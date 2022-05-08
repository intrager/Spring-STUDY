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
		return "Validator 1";
	}
	
	@RequestMapping("/insertForm")
	public String insertOne() {
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insertTwo(@ModelAttribute("dto") ContentDTO contentDTO, BindingResult result) {
		// 커맨드 객체 파라미터로 폼 데이터 받아서 처리
		String page = "createDonePage";
		System.out.println(contentDTO);
		
		ContentValidator validator = new ContentValidator();	// 유효성을 검증할 객체 생성
		validator.validate(contentDTO, result);	// 검증
		if(result.hasErrors()) {	// result에 값이 있으면 에러가 있다는 뜻
			page = "createPage";
		}
		
		return page;
	}
}
