package com.study.hellospring;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Valid_Annotation 4";
	}
	
	@RequestMapping("/insertForm")
	public String insertOne() {
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insertTwo(@ModelAttribute("dto") @Valid ContentDTO contentDTO, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDTO);
		
		// ContentValidator validator = new ContentValidator();
		// validator.validate(contentDTO, result);
		if(result.hasErrors()) {
			
			/*
				if(result.getFieldError("writer") != null) {
					System.out.println("1 : " + result.getFieldError("writer").getCode());
				}
				if(result.getFieldError("content") != null) {
					System.out.println("2 : " + result.getFieldError("content").getCode());
				} 
			*/
			if(result.getFieldError("writer") != null) {
				System.out.println("1 : " + result.getFieldError("writer").getDefaultMessage());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("2 : " + result.getFieldError("content").getDefaultMessage());
			}
			page = "createPage";
		}
		return page;
	}
	/*
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(new ContentValidator());
		}
	*/
}
