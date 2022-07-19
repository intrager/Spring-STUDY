package com.study.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	// try~catch 대신에 씀
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model model) {
		model.addAttribute("ex", ex);	// 예외 정보가 모델에 담김
		return "error";
	}	

	@ExceptionHandler(Exception.class)	// 어떤 예외일 때 호출해야하는지 적어줘야함
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)// 200 -> 500
	public String catcher(Exception ex, Model model) {
		System.out.println("model = " + model);
		System.out.println("catcher() in ExceptionController");
		//model.addAttribute("ex", ex);
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main(Model model) throws Exception {
		model.addAttribute("msg", "message from ExceptionController.main()");
		throw new Exception("에러가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new FileNotFoundException("예외가 발생했습니다.");
	}
}
