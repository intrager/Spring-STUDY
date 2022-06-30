package com.study.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.hellospring.dao.ISimpleBbsDAO;

@Controller
public class MyController {
	@Autowired
	ISimpleBbsDAO iSimpleBbsDao;
	
	@RequestMapping("/")
	public String root() throws Exception {
		// MyBatis : ResultNum
		return "redirect:list";
	}
	
	
	
}
