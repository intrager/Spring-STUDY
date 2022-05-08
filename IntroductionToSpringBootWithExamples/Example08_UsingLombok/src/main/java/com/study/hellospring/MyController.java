package com.study.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Form 데이터 전달받아 사용하기";
	}
	
	/**
	 * 파라미터와 이름이 같은 변수를 가진 커맨드 객체를 이용하면 쉽고 간편하게 많은 데이터를 받아서 처리할 수 있다.
	 * 이 경우 모델과 별도로 커맨드 객체 자체도 뷰에 전달된다.
	 */
	@RequestMapping("/test3")
	public String test3(Member member, Model model) {
		// 파라미터와 일치하는 빈을 만들어서 사용할 수 있다.
		// View 페이지에서 model을 사용하지 않고 member를 사용한다.
		return "test3";
	}
}
