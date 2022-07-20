package com.study.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@InitBinder	// 이 컨트롤러 클래스 내에서만 사용 가능 // 모든 컨트롤러에서 적용하려면 -> @WebBindingInitializer
	public void toDate(WebDataBinder binder) {
		
		ConversionService conversionService = binder.getConversionService();
		//System.out.println("conservsionService = " + conversionService);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));	// 구분자를 이용하여 String을 String[]로 바꿔라 feat. split()
//		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator());	// UserValidator를 WebDetaBinder의 로컬 Validator로 등록
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList = " + validatorList);
	}
	
//	@InitBinder
//	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})	// RequestMapping에 method를 안 적으면 GET, POST 둘 다 허용
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}

//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/save")	// Spring 4.3버전부터 사용 가능
	public String save(@Valid User user, BindingResult result, Model model) throws Exception {	// 파라미터 순서 조심
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		
		// 수동 검증 - Validator를 직접 생성하고, validate()를 직접 호출
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, result);	// BindingResult는 Errors의 하위 클래스
		
		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//			
//			model.addAttribute("msg", msg);
//			return "forward:/register/add";
////			return "redirect:/register/add?msg=" + msg;	// URL 재작성(rewriting)
//		}
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
