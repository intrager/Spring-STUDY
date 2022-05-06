package com.study.hellospring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// MyController 클래스를 빈으로 등록하겠다
public class MyController {
	@Autowired	// 빈이 생성될 때 member1 변수가 참조할 객체를 자동으로 찾아온다.
	Member member1;
	
	@Autowired	// 빈이 생성될 때 printer 변수가 참조할 객체를 자동으로 찾아온다.
	@Qualifier("printerB")	// 유사한 객체가 printerA, printerB 등 여러 개일 때 빈의 이름으로 정확하게 지정한다.
	Printer printer;
	
	@Autowired	// Member 클래스 타입으로 등록된 빈이 하나밖에 없으므로 추가 정보 없이도 잘 찾아올 수 있다.
	Member member2;
	
	@RequestMapping("/")	// 웹 브라우저에서 사용자가 /로 get 방식의 url을 호출하면 다음 라인의 root() 메서드를 실행시킨다.
	public @ResponseBody String root() {	
		/* 
		 * @ResponseBody 애노테이션은 JSON이나 XML 등 REST API 형태의 응답을 할 경우,
		 * 다시 말해 HTML 태그 없이 순수하게 스트링 데이터만으로 응답할 경우 지정한다.
		*/
		// 1.Member Bean 가져오기
		member1.print();
		
		// 2.PrinterB Bean 가져오기
		member1.setPrinter(printer);
		member1.print();
		
		// 3.싱글톤인지 확인
		if(member1 == member2) System.out.println("동일한 객체입니다.");
		else System.out.println("서로 다른 객체입니다.");
	
		return "Annotation 실습";
	}
}
