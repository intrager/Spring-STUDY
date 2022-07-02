package com.study.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.hellospring.dao.Transaction3DAO;

@Service
public class LogWriteService {
	@Autowired
	Transaction3DAO transaction3DAO;
	
	public int write(String consumerId, int amount) {
		try {
			transaction3DAO.pay(consumerId, amount);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
}
