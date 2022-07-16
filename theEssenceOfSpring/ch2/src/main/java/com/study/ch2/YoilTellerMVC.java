package com.study.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	
	@RequestMapping("/getYoilMVC")
	public ModelAndView main(int year, int month, int day) throws IOException {
		
		// 1. ModelAndView를 생성하고, 기본 뷰를 지정
		ModelAndView mv = new ModelAndView();
	
		// 2. 유효성 검사
		if(!isValid(year, month, day))
			return mv;
		
		// 2. 요일 계산
		char yoil = getYoil(year, month, day);
		
		// 3. 계산한 결과를 model에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
	
		// 4. 결과를 보여줄 view 지정
		mv.setViewName("yoil");
		
		return mv;
		//return "yoil";	// /WEB-INF/views/yoil.jsp
	}

	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:�Ͽ���, 2:������, ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}
