package com.study.hellospring.dto;

import lombok.Data;

@Data
public class SimpleBbsDTO {
	private int id;
	private String writer;
	private String title;
	private String content;
}
