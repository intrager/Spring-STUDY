package com.study.hellospring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Form 데이터 전달받아 사용하기";
	}
	
	/**
	 * JSP/Servlet에서 사용하던 전형적인 방법으로 리퀘스트(request)에서 파라미터의 이름으로 전달된 데이터를 추출한다.
	 * 다만, 흔히 개발자가 리퀘스트로부터 파라미터를 추출해야 하므로 이런 부분은 개발에 있어 소모적인 부분이 될 수 있다.
	 * 그래서 여기 메서드에서는 model에 추출한 값을 담은 후 리턴하고 있는 것을 알 수 있다.
	 */
	@RequestMapping("/test1")	
	public String test1(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		String name = httpServletRequest.getParameter("name");
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test1";
	}
	
	/**
	 * @RequestParam 에노테이션으로 파라미터 변수에 직접 값을 넣어주고 있다. test1 메서드에서 사용한 방식에 비해 간편하지만,
	 * 파라미터가 많아지면 유지보수에 어려움이 발생할 수 있다.
	 */
	@RequestMapping("/test2")
	public String test2(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {
		// 파라미터가 많아지면 불편해진다.
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test2";
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
	
	/**
	 * Path 자체에 변수를 넣어줄 수도 있다. 
	 * 이 경우 아무런 표시가 없다면 주소인지 변수인지 알 수 없기 때문에 변수라고 알려줘야 사용할 수 있다. 
	 */
	// Path 자체에 변수를 넣을 수도 있다.
	@RequestMapping("/test4/{studentId}/{name}")
	public String getStudent(@PathVariable String studentId, @PathVariable String name, Model model) {
		model.addAttribute("id", studentId);
		model.addAttribute("name", name);
		return "test4";
	}
}
