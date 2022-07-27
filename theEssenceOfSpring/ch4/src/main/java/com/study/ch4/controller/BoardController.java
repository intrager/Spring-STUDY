package com.study.ch4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.ch4.domain.BoardDto;
import com.study.ch4.domain.PageHandler;
import com.study.ch4.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
		String writer = (String) session.getAttribute("id");
		try {
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			
			int rowCnt = boardService.remove(bno, writer);
			
			if(rowCnt != 1)
				throw new Exception("board remove error");
						
			rattr.addFlashAttribute("msg", "Delete Success");
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "Delete Error");
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, Model m) {
		try {
			BoardDto boardDto = boardService.read(bno);
//			m.addAttribute("boardDto", boardDto);	// 아래 문장과 동일
			m.addAttribute(boardDto);	// 타입의 첫글자가 소문자가 된 게 이름으로 저장됨
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	
	@GetMapping("/list")
	public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/login/login?toURL=" + request.getRequestURL();	// 로그인을 안 했으면 로그인 화면으로 이동
		
		if(page == null) page = 1;
		if(pageSize == null) pageSize = 10;
		
		try {
			int totalCnt = boardService.getCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
			
			List<BoardDto> list = boardService.getPage(map);
			
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page",page);
			m.addAttribute("pageSize",pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boardList";	// 로그인을 한 상태이면, 게시판 화면으로 이동
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 세션을 얻어서
		HttpSession session = request.getSession();
		// 세션에 id가 있는지 확인하고 있으면 true 반환
		return session.getAttribute("id") != null;
	}
}
