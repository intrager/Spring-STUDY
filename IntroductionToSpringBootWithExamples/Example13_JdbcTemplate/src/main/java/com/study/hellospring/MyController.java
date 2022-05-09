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
	
	@Autowired	// 자동 주입
	private MyUserDAO userDAO;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "JdbcTemplate 사용하기";
	}
	
	// @GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userListPage(Model model) {
		model.addAttribute("users", userDAO.list());
		return "userList";
	}
}