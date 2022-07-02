package com.study.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.hellospring.dao.Transaction1DAO;
import com.study.hellospring.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService {
	@Autowired
	Transaction1DAO transaction1DAO;
	@Autowired
	Transaction2DAO transaction2DAO;
	
	@Override
	public int buy(String consumerId, int amount, String error) {
		try {
			transaction1DAO.pay(consumerId, amount);
			
			// 의도적으로 발생시키는 에러
			if (error.equals("1")) { int n = 10 / 0; }
			
			transaction2DAO.pay(consumerId, amount);
			
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
