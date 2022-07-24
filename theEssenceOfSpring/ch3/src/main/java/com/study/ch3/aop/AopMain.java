package com.study.ch3.aop;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

public class AopMain {
	public static void main(String[] args) throws Exception {
		MyAdvice myAdvice = new MyAdvice();
	
		Class myClass = Class.forName("com.study.ch3.aop.MyClass");
		Object obj = myClass.newInstance();
		
		// myClass에 정의되어있는 methods를 배열로 얻어서 하나씩 꺼내서 반복문으로 돌림
		for(Method m : myClass.getDeclaredMethods()) {  
			myAdvice.invoke(m, obj, null); // invoke()에다가 myClass에 정의된 메서드 정보 넘겨줌
		}
	}
}

class MyAdvice {
	Pattern p = Pattern.compile("a.*");	// 메서드 이름이 a로 시작하는 것들만 고름
	
	boolean matches(Method m) { // 패턴에 맞는 것만 골라서 코드에 추가시킴
		Matcher matcher = p.matcher(m.getName());
		return matcher.matches();
	}
	
	void invoke(Method m, Object obj, Object...args) throws Exception {
		if(m.getAnnotation(Transactional.class) != null)
			System.out.println("[before]{");
		
		m.invoke(obj, args);	// aaa(), aaa2(), bbb() 호출가능
		if(m.getAnnotation(Transactional.class) != null)		// Practice : matches(m)
			System.out.println("}[after]");
	}
}

class MyClass {
	@Transactional
	void aaa() {
		System.out.println("aaa() is called.");
	}
	void aaa2() {
		System.out.println("aaa2() is called.");
	}
	void ccc() {
		System.out.println("ccc() is called.");
	}
}
