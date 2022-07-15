package com.study.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	// 1. ���� ȣ�� ������ ���α׷����� ���
public class Hello {
	int iv = 10;	// �ν��Ͻ� ����
	static int sv = 20;	// static ����
	
	// 2. URL�� �޼��带 ����
	@RequestMapping("/hello")
	private void main() {	// �ν��Ͻ� �޼��� - iv, sv �� �� ��� ����
		System.out.println("Hello - private");
		System.out.println(sv); // ����
		System.out.println(iv);	// ����
	}
	
	public static void main2() {	// static �޼��� - sv�� ��� ����
		System.out.println(sv);	// ����
		// System.out.println(iv);	// ����
	}
}
