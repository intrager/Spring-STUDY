package com.study.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
//	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})	// RequestMapping에 method를 안 적으면 GET, POST 둘 다 허용
//	@GetMapping("/register/add")
//	public String register() {
//		return "registerForm";	// WEB-INF/views/registerForm.jsp
//	}

//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/register/save")	// Spring 4.3버전부터 사용 가능
	public String save(User user, Model model) throws Exception {
		// 1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
			
			model.addAttribute("msg", msg);
			return "redirect:/register/add";
//			return "redirect:/register/add?msg=" + msg;	// URL 재작성(rewriting)
		}
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
