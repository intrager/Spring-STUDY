package com.study.hellospring;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Valid_initBinder 3";
	}
	
	@RequestMapping("/insertForm")
	public String insertOne() {
		return "createPage";
	}
	
	/**
	 * @Valid로 contentDTO 객체 변수에 대한 유효성 검증하겠다고 표시
	 * 파라미터로 객체 변수가 들어오면 스프링이 binder 변수에 저장된 객체를 통해서 즉시 유효성을 검사함
	 * 만약 에러가 있다면 result 변수에 담아둠.
	 */
	@RequestMapping("/create")
	public String insertTwo(@ModelAttribute("dto")	@Valid ContentDTO contentDTO, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDTO);
		
		// ContentValidator validator = new ContentValidator();
		// validator.validate(contentDTO, result);
		if(result.hasErrors()) {
			if(result.getFieldError("writer") != null) {	// 에러가 없다면 정상임
				System.out.println("1 : " + result.getFieldError("writer").getCode());
			}
			if(result.getFieldError("content") != null) {	// 에러가 없다면 정상임
				System.out.println("2 : " + result.getFieldError("content").getCode());
			}
			page = "createPage";
		}
		return page;
	}
	
	/**
	 * 해당 메서드를 프로젝트가 시작할 때 먼저 실행시킴.
	 * 그러면 WebDataBinder 타입 변수에 우리가 사용할 유효성 검증 클래스가 프로젝트 시작할 때 등록됨.
	 * 프로젝트에서 사용할 빈을 등록하는 것과 비슷하지만, 관리하는 곳을 다르게 지정함.
	 * 이후로는 개별적으로 생성할 필요 없이 검증이 필요하면 binder 변수에서 꺼내서 사용하면 됨.
	 * 다른 곳에서는 필요할 때마다 매번 new로 만들지 않고 
	 * 한 번 만들어놓은 것을 주입 받아 사용할 수 있게 되었으므로 약한 결합으로 사용할 수 있게 됨.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ContentValidator());
	}
}
