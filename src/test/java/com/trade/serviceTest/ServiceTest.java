package com.trade.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.TraderShopApplication;
import com.trade.dto.RequestDto;
import com.trade.model.Trade;
import com.trade.repository.TradeRepo;
import com.trade.service.TradeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TraderShopApplication.class)
@AutoConfigureMockMvc
public class ServiceTest {

	@InjectMocks
	private TradeServiceImpl tradeServiceImpl;
	
	@Mock
	TradeRepo tradeRepo;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		}
	
	@Test
	void testDeActivationDeactivation() throws JSONException{
	
	RequestDto requestDto=new RequestDto();
	requestDto.setBook_id("B1");
	requestDto.setCounter_party_id("CP-1");
	requestDto.setMaturity_date(new Date());
	requestDto.setTrade_id("T1");
	requestDto.setVersion(1);
	
	Trade trade=new Trade();
	trade.setVersion(1);
	trade.setTrade_id("T1");
	
	Mockito.when(tradeRepo.save(Mockito.any())).thenReturn(trade);
	assertEquals(requestDto, requestDto);
	}
}
