package com.study.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class BuyAndLogService {
	@Autowired
	BuyTicketService buyTicketService;
	@Autowired
	LogWriteService logWrite;

	@Autowired
	TransactionTemplate transactionTemplate;

	public int buy(String consumerId, int amount, String error) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					buyTicketService.buy(consumerId, amount, error);
					
					// 의도적으로 발생시키는 에러
					if(error.equals("2")) { int n = 10 / 0; }
					
					logWrite.write(consumerId, amount);
				}
			});
			return 1;
		} catch(Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback");
			return 0;
		}
	}
}
