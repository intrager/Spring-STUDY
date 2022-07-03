package com.study.hellospring.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		String loginId = request.getParameter("j_username");
		String errorMsg = "";
		
		if(exception instanceof BadCredentialsException) {
			loginFailureCount(loginId);
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(loginId);
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요";
		}
		
		request.setAttribute("username", loginId);
		request.setAttribute("error_message", errorMsg);
		
		request.getRequestDispatcher("/loginForm?error=true").forward(request, response);
	}
	
	// 비밀번호를 세 번 이상 틀릴 시 계정 잠금 처리
	protected void loginFailureCount(String username) {
		/*
		 * // 틀린 횟수 업데이트
		 * userDAO.countFailure(username);
		 * 
		 * // 틀린 횟수 조회
		 * int cnt = userDAO.checkFailureCount(username);
		 * if(cnt = 3) {
		 * 	// 계정 잠금 처리
		 * 	userDAO.disabledUsername(username);
		 * }
		 */
	}
}
