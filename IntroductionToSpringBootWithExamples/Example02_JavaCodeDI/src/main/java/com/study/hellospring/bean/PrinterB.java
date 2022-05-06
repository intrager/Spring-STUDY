package com.study.hellospring.bean;

public class PrinterB implements Printer { 
	@Override
	public void print(String message) {
		System.out.println("Printer B : " + message);
	}
}
