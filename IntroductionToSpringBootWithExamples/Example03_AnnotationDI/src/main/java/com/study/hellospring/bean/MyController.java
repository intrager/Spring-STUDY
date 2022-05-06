package com.study.hellospring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// MyController Ŭ������ ������ ����ϰڴ�
public class MyController {
	@Autowired	// ���� ������ �� member1 ������ ������ ��ü�� �ڵ����� ã�ƿ´�.
	Member member1;
	
	@Autowired	// ���� ������ �� printer ������ ������ ��ü�� �ڵ����� ã�ƿ´�.
	@Qualifier("printerB")	// ������ ��ü�� printerA, printerB �� ���� ���� �� ���� �̸����� ��Ȯ�ϰ� �����Ѵ�.
	Printer printer;
	
	@Autowired	// Member Ŭ���� Ÿ������ ��ϵ� ���� �ϳ��ۿ� �����Ƿ� �߰� ���� ���̵� �� ã�ƿ� �� �ִ�.
	Member member2;
	
	@RequestMapping("/")	// �� ���������� ����ڰ� /�� get ����� url�� ȣ���ϸ� ���� ������ root() �޼��带 �����Ų��.
	public @ResponseBody String root() {	
		/* 
		 * @ResponseBody �ֳ����̼��� JSON�̳� XML �� REST API ������ ������ �� ���,
		 * �ٽ� ���� HTML �±� ���� �����ϰ� ��Ʈ�� �����͸����� ������ ��� �����Ѵ�.
		*/
		// 1.Member Bean ��������
		member1.print();
		
		// 2.PrinterB Bean ��������
		member1.setPrinter(printer);
		member1.print();
		
		// 3.�̱������� Ȯ��
		if(member1 == member2) System.out.println("������ ��ü�Դϴ�.");
		else System.out.println("���� �ٸ� ��ü�Դϴ�.");
	
		return "Annotation �ǽ�";
	}
}
