package com.study.hellospring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.hellospring.dto.SimpleBbsDTO;

@Mapper	// 다음 인터페이스의 구현을 XML로 한다는 의미
public interface ISimpleBbsDAO {
	
	public List<SimpleBbsDTO> listDAO();
	public SimpleBbsDTO viewDAO(String id);
	public int writeDAO(String writer, String title, String content);
	public int deleteDAO(@Param("_id") String id);
}
