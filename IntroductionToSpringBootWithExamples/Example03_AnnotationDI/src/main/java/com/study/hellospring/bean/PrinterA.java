package com.study.hellospring.bean;

import org.springframework.stereotype.Component;

@Component("printerA")	// 다음에 나오는 클래스인 PrinterA 클래스를 빈으로 등록하겠다
public class PrinterA implements Printer {

	@Override
	public void print(String message) {
		System.out.println("Printer A : " + message);
	}

}
