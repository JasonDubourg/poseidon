package com.nnk.springboot.ut.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	TradeService tradeService;

	Trade tradeTest1 = new Trade("account1", "type1");
	Trade tradeTest2 = new Trade("account2", "type2");
	Trade tradeTest3 = new Trade("account3", "type3");

	List<Trade> tradesListTest = new ArrayList<Trade>();

	@Test
	public void testFindAllTrades() {
		tradesListTest.add(tradeTest1);
		tradesListTest.add(tradeTest2);
		tradesListTest.add(tradeTest3);
		Mockito.when(tradeService.findAllTrades()).thenReturn(tradesListTest);
		assertEquals(3, tradesListTest.size());
	}

	@Test
	public void testFindTradeById() {
		Optional<Trade> tradeTest4 = Optional.of(new Trade());
		Mockito.when(tradeService.findById(1)).thenReturn(tradeTest4);
		assertNotNull(tradeTest4);
	}

	@Test
	public void testSaveTrade() {
		String ref = "account1";
		Mockito.when(tradeService.save(tradeTest1)).thenReturn(tradeTest1);
		assertEquals(ref, tradeTest1.getAccount());
	}

	@Test
	public void testDeleteTrade() {
		Mockito.doNothing().when(tradeService).delete(tradeTest2);
		verify(tradeService, times(1));
	}
}
