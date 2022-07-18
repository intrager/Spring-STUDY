package com.study.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

// @ControllerAdvice // 모든 패키지에 적용
@ControllerAdvice("com.study.ch3")	// 지정된 패키지에서 발생한 예외만 처리
public class GlobalCatcher {

	// try~catch 대신에 씀
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model model) {
		model.addAttribute("ex", ex);	// 예외 정보가 모델에 담김
		return "error";
	}
	
	@ExceptionHandler(Exception.class)	// 어떤 예외일 때 호출해야하는지 적어줘야함
	public String catcher(Exception ex, Model model) {
		model.addAttribute("ex", ex);
		return "error";
	}
	
}
