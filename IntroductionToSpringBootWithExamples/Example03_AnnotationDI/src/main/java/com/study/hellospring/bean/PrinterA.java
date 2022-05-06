package com.study.hellospring.bean;

import org.springframework.stereotype.Component;

@Component("printerA")	// ������ ������ Ŭ������ PrinterA Ŭ������ ������ ����ϰڴ�
public class PrinterA implements Printer {

	@Override
	public void print(String message) {
		System.out.println("Printer A : " + message);
	}

}
