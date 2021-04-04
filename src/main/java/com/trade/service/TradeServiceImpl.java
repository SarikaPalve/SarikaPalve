package com.trade.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.dto.RequestDto;
import com.trade.model.Trade;
import com.trade.repository.TradeRepo;

@Service
public class TradeServiceImpl {

	@Autowired
	private TradeRepo tradeRepo;

	public RequestDto saveTradeDetails(RequestDto request) throws Exception {

		if (request.getMaturity_date().after(new Date())) {

			Trade tradeData = tradeRepo.findByTradeId(request.getTrade_id(), request.getVersion());
			if (tradeData == null) {
				Trade trade = new DozerBeanMapper().map(request, Trade.class);
				trade.setCreated_date(new Date());
				trade.setExpired("N");
				trade = tradeRepo.save(trade);
			} else if (tradeData != null && tradeData.getVersion() < request.getVersion()) {
				Trade trade = new DozerBeanMapper().map(request, Trade.class);
				trade.setCreated_date(new Date());
				trade.setExpired("N");
				trade = tradeRepo.save(trade);
			} else if (tradeData != null && tradeData.getVersion() == request.getVersion()) {
				tradeData.setBook_id(request.getBook_id());
				tradeData.setCounter_party_id(request.getCounter_party_id());
				tradeData.setCreated_date(new Date());
				tradeData.setExpired("N");
				tradeData.setMaturity_date(request.getMaturity_date());
				tradeData = tradeRepo.save(tradeData);

			} else {
				throw new Exception("Version is lower");
			}
		} else {
			throw new Exception("Maturity Date should not less than today's date");
		}

		return request;
	}

	public void updateFlag() {

		List<Trade> tradeData = tradeRepo.findByExpiredFlag();
		if (tradeData != null && !tradeData.isEmpty()) {
			List<Trade> tradeList = tradeData.stream().filter(trade -> trade.getMaturity_date().before(new Date()))
					.collect(Collectors.toList());

			if (!tradeList.isEmpty()) {
				for (Trade trade2 : tradeList) {
					trade2.setExpired("Y");
					tradeRepo.save(trade2);
				}
			}

		}
	}

}
