package com.study.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.hellospring.dao.Transaction1DAO;
import com.study.hellospring.dao.Transaction2DAO;

@Service
public class BuyTicketService {
	@Autowired
	Transaction1DAO transaction1DAO;
	@Autowired
	Transaction2DAO transaction2DAO;
	
	@Autowired
	TransactionTemplate transactionTemplate;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int buy(String consumerId, int amount, String error) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					transaction1DAO.pay(consumerId, amount);
					
					// 의도적으로 발생시키는 에러
					if(error.equals("1")) { int n = 10 / 0; }
					
					transaction2DAO.pay(consumerId, amount);
				}
			});
			return 1;
		} catch(Exception e) {
			System.out.println("[Transaction Propagation #2] Rollback");
			return 0;
		}
	}
}
