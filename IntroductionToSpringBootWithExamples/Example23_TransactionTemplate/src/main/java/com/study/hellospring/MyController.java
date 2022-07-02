package com.study.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.hellospring.service.BuyTicketService;

@Controller
public class MyController {
	@Autowired
	BuyTicketService buyTicketService;

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Transaction Manager (3)";
	}
	
	@RequestMapping("/buyTicket")
	public String buyTicket() {
		return "buyTicket";
	}
	
	@RequestMapping("/buyTicketCard")
	public String buyTicketCard(@RequestParam("consumerId") String consumerId,
			@RequestParam("amount") String amount,
			@RequestParam("error") String error,
			Model model) {
		int nResult = buyTicketService.buy(consumerId, Integer.parseInt(amount), error);

		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		if(nResult == 1) {
			return "buyTicketEnd";
		} else {
			return "buyTicketError";
		}
	}
}
