package com.study.hellospring.bean;

import org.springframework.stereotype.Component;

@Component("printerB")	// ������ ������ PrinterB Ŭ������ ������ ����ϰڴ�
public class PrinterB implements Printer { 
	@Override
	public void print(String message) {
		System.out.println("Printer B : " + message);
	}
}
