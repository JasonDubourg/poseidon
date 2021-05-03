package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepository;

	public List<Rating> findAllRatings() {
		return ratingRepository.findAll();
	}

	public Optional<Rating> findById(int id) {
		return ratingRepository.findById(id);
	}

	public Rating save(Rating rating) {
		return ratingRepository.save(rating);
	}

	public void delete(Rating rating) {
		ratingRepository.delete(rating);
	}
}
