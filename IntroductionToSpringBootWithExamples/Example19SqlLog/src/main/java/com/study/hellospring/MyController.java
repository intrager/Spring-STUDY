package com.study.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.hellospring.jdbc.MyUserDAO;

@Controller
public class MyController {
	/**
	 * '자식 객체를 부모 타입의 변수에 대입할 수 있다'
	 */
	@Autowired
	private MyUserDAO myUserDAO;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "MyBatis 사용하기";
	}
	
	// @GetMapping("/user")
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public String userListPage(Model model) {
		model.addAttribute("users", myUserDAO.list());
		return "userList";
	}
	
}
