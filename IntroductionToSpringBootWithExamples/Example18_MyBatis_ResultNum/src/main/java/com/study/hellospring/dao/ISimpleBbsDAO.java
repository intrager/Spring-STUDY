package com.study.hellospring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ISimpleBbsDAO {
	public List<SimpleBbsDTO> listDao();
	public SimpleBbsDTO viewDao(String id);
	public int writeDao(Map<String, String> map);
	public int deleteDao(@Param("_id") String id);
	public int articleCount();
}
