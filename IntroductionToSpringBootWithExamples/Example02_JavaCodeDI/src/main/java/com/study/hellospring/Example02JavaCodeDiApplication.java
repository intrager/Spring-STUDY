package com.study.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.hellospring.bean.Config;
import com.study.hellospring.bean.Member;
import com.study.hellospring.bean.Printer;

//@SpringBootApplication
public class Example02JavaCodeDiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Example02JavaCodeDiApplication.class, args);
		
		// 1.IoC �����̳� ����
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		// 2.Member Bean ��������
		Member member1 = (Member)context.getBean("member1");
		member1.print();
		
		// 3.Member Bean ��������
		Member member2 = context.getBean("hello", Member.class);
		member2.print();
		
		// 4.PrinterB Bean ��������
		Printer printer = context.getBean("printerB", Printer.class);
		member1.setPrinter(printer);
		member1.print();
		
		// 5.�̱������� Ȯ��
		// ���� Ŭ���� Ÿ���� ����������, ���� �ٸ� �޼��忡�� ������ ��ü��.
		if(member1 == member2) System.out.println("������ ��ü�Դϴ�.");
		else System.out.println("���� �ٸ� ��ü�Դϴ�.");
	}

}
