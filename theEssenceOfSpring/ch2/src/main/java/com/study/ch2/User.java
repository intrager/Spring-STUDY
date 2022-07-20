package com.study.ch2;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private String id;
	private String pw;
	private String name;
	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd")	// 적용할 포맷만 적용
	private Date birth;
	private String[] hobby;
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	private String[] sns;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
//	public String[] getHobby() {
//		return hobby;
//	}
//	public void setHobby(String[] hobby) {
//		this.hobby = hobby;
//	}
	public String[] getSns() {
		return sns;
	}
	public void setSns(String[] sns) {
		this.sns = sns;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email 
				+ ", birth=" + birth + ", hobby=" + Arrays.toString(hobby) + ", sns=" + Arrays.toString(sns) + "]";
	}
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email 
//				+ ", birth=" + birth + ", hobby=" + Arrays.toString(hobby) + ", sns=" + Arrays.toString(sns) + "]";
//	}

	
}
