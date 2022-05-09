package com.study.hellospring.dao;

import java.util.List;

import com.study.hellospring.dto.SimpleBbsDTO;

public interface SimpleBbsDAO {
	public List<SimpleBbsDTO> listDAO();
	public SimpleBbsDTO viewDAO(String id);
	public int writeDAO(String writer, String title, String content);
	public int deleteDAO(String id);
}
