package com.study.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "ValidationUtils 2";
	}
	
	@RequestMapping("/insertForm")
	public String insertOne() {
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insertTwo(@ModelAttribute("dto") ContentDTO contentDTO, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDTO);
		
		ContentValidator validator = new ContentValidator();
		validator.validate(contentDTO, result);
		
		/*
		 * Example09에서는 ContentValidator 클래스에서 유효성 검증 에러를 출력했지만,
		 * 이번 예제에서는 스프링에서 제공되는 API를 사용하면서 에러를 담은 결과만 리턴받는다.
		 */
		if(result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());
			
			if(result.getFieldError("writer") != null) {
				System.out.println("1 : " + result.getFieldError("writer").getCode());
			}
			
			if(result.getFieldError("content") != null) {
				System.out.println("2 : " + result.getFieldError("content").getCode());
			}
			page = "createPage";
		}
		return page;
	}
}
