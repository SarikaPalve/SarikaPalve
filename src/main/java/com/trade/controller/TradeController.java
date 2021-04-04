package com.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.dto.RequestDto;
import com.trade.service.TradeServiceImpl;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class TradeController {

	@Autowired
	private TradeServiceImpl tradeService;

	@PostMapping("v1/save-trade-record")
	public RequestDto saveTradeDetails(@RequestBody RequestDto request) throws Exception {
		return tradeService.saveTradeDetails(request);
	}
	
	@Scheduled(cron = "${job.updateFlag}")
	public void updateFlag()
	{
		tradeService.updateFlag();
	}

}
