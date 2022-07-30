package com.study.ch4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.ch4.domain.Person;

@Controller
public class SimpleRestController {
	
//	@GetMapping("/ajax")
//	public String ajax() {
//		return "ajax";
//	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/send")
//	@ResponseBody
	public Person test(@RequestBody Person p) {
		System.out.println("p = " + p);
		p.setName("ABC");
		p.setAge(p.getAge() + 10);
		
		return p;
	}
	
//	@PostMapping("/send")
////	@ResponseBody
//	public Person test2(@RequestBody Person p) {
//		System.out.println("p = " + p);
//		p.setName("ABC");
//		p.setAge(p.getAge() + 10);
//		
//		return p;
//	}
}
