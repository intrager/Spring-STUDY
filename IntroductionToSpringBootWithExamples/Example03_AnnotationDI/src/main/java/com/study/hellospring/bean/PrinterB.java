package com.study.hellospring.bean;

import org.springframework.stereotype.Component;

@Component("printerB")	// 다음에 나오는 PrinterB 클래스를 빈으로 등록하겠다
public class PrinterB implements Printer { 
	@Override
	public void print(String message) {
		System.out.println("Printer B : " + message);
	}
}
