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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	RatingService ratingService;

	Rating ratingTest1 = new Rating("moodysRating1", "sandP1", "fitch1", 12);
	Rating ratingTest2 = new Rating("moodysRating2", "sandP2", "fitch2", 24);

	List<Rating> ratingsListTest = new ArrayList<Rating>();

	@Test
	public void testFindAllRatings() {
		ratingsListTest.add(ratingTest1);
		ratingsListTest.add(ratingTest2);
		Mockito.when(ratingService.findAllRatings()).thenReturn(ratingsListTest);
		assertEquals(2, ratingsListTest.size());
	}

	@Test
	public void testFindRatingById() {
		Optional<Rating> ratingTest3 = Optional.of(new Rating());
		Mockito.when(ratingService.findById(1)).thenReturn(ratingTest3);
		assertNotNull(ratingTest3);
	}

	@Test
	public void testSaveRating() {
		Integer ref = 12;
		Mockito.when(ratingService.save(ratingTest1)).thenReturn(ratingTest1);
		assertEquals(ref, ratingTest1.getOrderNumber());
	}

	@Test
	public void testDeleteRating() {
		Mockito.doNothing().when(ratingService).delete(ratingTest2);
		verify(ratingService, times(1));
	}
}
