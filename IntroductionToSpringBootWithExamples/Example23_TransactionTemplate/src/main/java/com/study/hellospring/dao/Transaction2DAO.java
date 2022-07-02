package com.study.hellospring.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction2DAO {
	public void pay(String consumerId, int amount);
}
