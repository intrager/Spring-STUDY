package com.study.hellospring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.hellospring.dto.SimpleBbsDTO;

@Mapper
public interface ISimpleBbsDAO {
	
	public List<SimpleBbsDTO> listDAO();
	public SimpleBbsDTO viewDAO(String id);
	public int writeDAO(Map<String, String> map);
	public int deleteDAO(@Param("_id") String id);
	public int articleCount();
}
