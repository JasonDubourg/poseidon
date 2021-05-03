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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	BidListService bidListService;

	BidList bidListTest1 = new BidList("Account1", "type1", 2.2);
	BidList bidListTest2 = new BidList("Account2", "type2", 4.4);

	List<BidList> bidsListTest = new ArrayList<BidList>();

	@Test
	public void testFindAllBids() {
		bidsListTest.add(bidListTest1);
		bidsListTest.add(bidListTest2);
		Mockito.when(bidListService.findAllBids()).thenReturn(bidsListTest);
		assertEquals(2, bidsListTest.size());
	}

	@Test
	public void testFindBidById() {
		Optional<BidList> bidListTest3 = Optional.of(new BidList());
		Mockito.when(bidListService.findById(1)).thenReturn(bidListTest3);
		assertNotNull(bidListTest3);
	}

	@Test
	public void testSaveBidList() {
		Double ref = 2.2;
		Mockito.when(bidListService.save(bidListTest1)).thenReturn(bidListTest1);
		assertEquals(ref, bidListTest1.getBidQuantity());
	}

	@Test
	public void testDeleteBidList() {
		Mockito.doNothing().when(bidListService).delete(bidListTest2);
		verify(bidListService, times(1));
	}
}
