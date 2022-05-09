package com.study.hellospring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.hellospring.dao.SimpleBbsDAO;

@Controller
public class MyController {
	
	@Autowired
	SimpleBbsDAO dao;
	
	@RequestMapping("/")
	public String root() throws Exception {
		// JdbcTemplate : SimpleBbs
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String userListPage(Model model) {
		model.addAttribute("list", dao.listDAO());
		return "list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", dao.viewDAO(sId));
		return "view";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping("/write")
	public String write(Model model, HttpServletRequest request) {
		dao.writeDAO(request.getParameter("writer"), 
					request.getParameter("title"), 
					request.getParameter("content"));
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.deleteDAO(request.getParameter("id"));
		return "redirect:list";
	}
}
