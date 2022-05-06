package com.study.hellospring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component	// Member Ŭ������ ������ ����ϰڴ�
public class Member {
	@Value("ȫ�浿")	// ���� ������ �� name ������ �⺻������ "ȫ�浿" ����
	private String name;
	@Value("����")	// ���� ������ �� nickname ������ �⺻������ "����"�� �����Ѵ�.
	private String nickname;
	@Autowired		// ���� ������ �� printer ������ ������ ��ü�� �ڵ����� ã�ƿ´�.
	@Qualifier("printerA")	// ������ ��ü�� ���� ���� �� ���� �̸����� ��Ȯ�ϰ� �����Ѵ�.
	private Printer printer;
	
	public Member() {}
	
	public Member(String name, String nickname, Printer printer) {
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	public void print() {
		printer.print("Hello " + name + " : " + nickname);
	}
}
