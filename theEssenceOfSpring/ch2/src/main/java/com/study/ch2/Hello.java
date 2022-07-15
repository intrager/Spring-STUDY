package com.study.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	// 1. 원격 호출 가능한 프로그램으로 등록
public class Hello {
	int iv = 10;	// 인스턴스 변수
	static int sv = 20;	// static 변수
	
	// 2. URL과 메서드를 연결
	@RequestMapping("/hello")
	private void main() {	// 인스턴스 메서드 - iv, sv 둘 다 사용 가능
		System.out.println("Hello - private");
		System.out.println(sv); // 가능
		System.out.println(iv);	// 가능
	}
	
	public static void main2() {	// static 메서드 - sv만 사용 가능
		System.out.println(sv);	// 가능
		// System.out.println(iv);	// 에러
	}
}
