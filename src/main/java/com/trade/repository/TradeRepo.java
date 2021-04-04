package com.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trade.model.Trade;

public interface TradeRepo extends JpaRepository<Trade, Integer> {

	@Query("select d from Trade d where d.trade_id=:tradeId and d.version=:version")
	Trade findByTradeId(String tradeId, Integer version);

	@Query("select d from Trade d where d.expired='N'")
	List<Trade> findByExpiredFlag();

}
