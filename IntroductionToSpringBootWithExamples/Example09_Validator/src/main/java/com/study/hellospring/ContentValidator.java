package com.study.hellospring;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> argOne) {
		return ContentDTO.class.isAssignableFrom(argOne);	// 검증할 객체의 클래스 타입 정보
	}
	
	@Override
	public void validate(Object obj, Errors errors) {	// 커맨드 객체를 파라미터로 받아들임
		ContentDTO dto = (ContentDTO) obj;	// 형변환 후 필드에 저장
		
		String sWriter = dto.getWriter();	// 커맨드 객체로부터 작성자 값을 구해와서 
		if(sWriter == null || sWriter.trim().isEmpty()) {	// 그 값이 NULL 혹은 비어있는지 체크
			System.out.println("Writer is null or empty");	// 데이터가 유효성 검사에서 통과하지 못 했다면
			errors.rejectValue("writer", "trouble");	//  errors 객체 변수에 에러 내용을 담음
		}
		
		String sContent = dto.getContent();
		if(sContent == null || sContent.trim().isEmpty()) {
			System.out.println("Content is null or empty");
			errors.rejectValue("content", "trouble");
		}
	}
}
