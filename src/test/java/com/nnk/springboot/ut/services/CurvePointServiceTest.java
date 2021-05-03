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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CurvePointService curvePointService;

	CurvePoint curvePointTest1 = new CurvePoint(1, 2.2, 3.3);
	CurvePoint curvePointTest2 = new CurvePoint(1, 4.4, 5.5);

	List<CurvePoint> curvePointsListTest = new ArrayList<CurvePoint>();

	@Test
	public void testFindAllCurvePoints() {
		curvePointsListTest.add(curvePointTest1);
		curvePointsListTest.add(curvePointTest1);
		Mockito.when(curvePointService.findAllCurvePoints()).thenReturn(curvePointsListTest);
		assertEquals(2, curvePointsListTest.size());
	}

	@Test
	public void testFindCurvePointById() {
		Optional<CurvePoint> curvePointTest3 = Optional.of(new CurvePoint());
		Mockito.when(curvePointService.findById(1)).thenReturn(curvePointTest3);
		assertNotNull(curvePointTest3);
	}

	@Test
	public void testSaveCurvePoint() {
		Double ref = 3.3;
		Mockito.when(curvePointService.save(curvePointTest1)).thenReturn(curvePointTest1);
		assertEquals(ref, curvePointTest1.getValue());
	}

	@Test
	public void testDeleteCurvePoint() {
		Mockito.doNothing().when(curvePointService).delete(curvePointTest2);
		verify(curvePointService, times(1));
	}
}
