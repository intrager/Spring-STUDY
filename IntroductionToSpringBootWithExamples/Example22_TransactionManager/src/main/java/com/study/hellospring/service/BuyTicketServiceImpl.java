package com.study.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.hellospring.dao.Transaction1DAO;
import com.study.hellospring.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService {
	@Autowired
	Transaction1DAO transaction1DAO;
	@Autowired
	Transaction2DAO transaction2DAO;

	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	TransactionDefinition transactionDefinition;
	
	@Override
	public int buy(String consumerId, int amount, String error) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		
		try {
			transaction1DAO.pay(consumerId, amount);
			
			// 의도적으로 발생시키는 에러
			if(error.equals("1")) { int n = 10 / 0; }
		
			transaction2DAO.pay(consumerId, amount);
			transactionManager.commit(transactionStatus);
			return 1;
		} catch(Exception e) {
			System.out.println("[PlatformTransactionManager] Rollback");
			transactionManager.rollback(transactionStatus);
			return 0;
		}
	}
}
