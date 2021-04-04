package com.trade.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.TraderShopApplication;
import com.trade.controller.TradeController;
import com.trade.dto.RequestDto;
import com.trade.service.TradeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TraderShopApplication.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeServiceImpl tradeServiceImpl;

	@InjectMocks
	private TradeController tradeController;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		}
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testSaveTradeData() throws Exception {
		
		RequestDto requestDto=new RequestDto();
		requestDto.setBook_id("B1");
		requestDto.setCounter_party_id("CP-1");
		requestDto.setMaturity_date(new Date());
		requestDto.setTrade_id("T1");
		requestDto.setVersion(1);
		
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		String expected = objectMapper.writeValueAsString(requestDto);

		Mockito.when(tradeServiceImpl.saveTradeDetails(requestDto))
				.thenReturn(requestDto);

		MvcResult result = mockMvc
				.perform(post("/api/v1/save-trade-record").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}
}
