package com.study.hellospring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component	// Member 클래스를 빈으로 등록하겠다
public class Member {
	@Value("홍길동")	// 빈이 생성될 때 name 변수의 기본값으로 "홍길동" 지정
	private String name;
	@Value("도사")	// 빈이 생성될 때 nickname 변수의 기본값으로 "도사"를 지정한다.
	private String nickname;
	@Autowired		// 빈이 생성될 때 printer 변수가 참조할 객체를 자동으로 찾아온다.
	@Qualifier("printerA")	// 유사한 객체가 여러 개일 때 빈의 이름으로 정확하게 지정한다.
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
