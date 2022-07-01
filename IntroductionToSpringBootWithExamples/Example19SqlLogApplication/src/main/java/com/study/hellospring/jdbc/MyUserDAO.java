package com.study.hellospring.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyUserDAO {
	List<MyUserDTO> list();
}
