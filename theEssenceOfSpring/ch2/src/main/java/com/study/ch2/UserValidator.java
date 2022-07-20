package com.study.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		/* 
		 어떤 객체를 검증하려고 하면, 그 객체의 클래스 객체가 넘어온다. 
		 그게 User 클래스하고 같으면, 검증하려는 객체가 User타입인 것
		*/
//		return User.class.equals(clazz);
		
		// clazz가 User 또는 그 자손인지 확인
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		if(!target instanceof User) return; // 위에 supports 메서드
		System.out.println("UserValidator.validate() is called");
		
		User user = (User)target;
		
		String id = user.getId();
		
//		if(id == null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		
		// errors 객체에다가 id라는 필드에 required 에러 코드를 넣어서 저장
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "required");
		
		if(id == null || id.length() < 5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength");
		}
	}

}
