package com.study.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 세션 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")				// rememberId가 String이면 value는 on, boolean이면 value가 true
	public String login(String id, String pw, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// 1. id와 pw를 확인
		if(!loginCheck(id, pw)) {
			// 2-1. id와 pw가 일치하지 않으면 loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pw가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg=" + msg;
		}
		
		// 2-2. id와 pw가 일치하면
		// 세션 객체를 얻어오기
		HttpSession session = request.getSession();
		// 세션 객체에 id를 저장
		session.setAttribute("id", id);
		if(rememberId) {
			// 1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			// 2. 응답에 저장
			response.addCookie(cookie);
		} else {
			// 1. 쿠키를 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);	// 쿠키를 삭제
			// 2. 응답에 저장
			response.addCookie(cookie);
		}
		
		// 3. 홈으로 이동
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pw) {
		return "qwer".equals(id) && "1234".equals(pw);
	}
}
