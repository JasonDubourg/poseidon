package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {
	// TODO: Inject Rating service ==> OK
	@Autowired
	RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		// TODO: find all Rating, add to model ==> OK
		List<Rating> ratings = ratingService.findAllRatings();
		model.addAttribute("ratings", ratings);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Rating list ==> OK
		if (!result.hasErrors()) {
			Rating newRating = new Rating();
			newRating.setMoodysRating(rating.getMoodysRating());
			newRating.setSandPRating(rating.getSandPRating());
			newRating.setFitchRating(rating.getFitchRating());
			newRating.setOrderNumber(rating.getOrderNumber());
			ratingService.save(newRating);
			model.addAttribute("ratings", ratingService.findAllRatings());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Rating by Id and to model then show to the form ==> OK
		Rating rating = ratingService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Rating and ==>
		// OK
		if (result.hasErrors()) {
			return "rating/update";
		}
		Rating rtg = ratingService.findById(id).get();
		rtg.setMoodysRating(rating.getMoodysRating());
		rtg.setSandPRating(rating.getSandPRating());
		rtg.setFitchRating(rating.getFitchRating());
		rtg.setOrderNumber(rating.getOrderNumber());
		ratingService.save(rtg);
		model.addAttribute("ratings", ratingService.findAllRatings());
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Rating by Id and delete the Rating, return to Rating list ==> OK
		Rating rating = ratingService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		ratingService.delete(rating);
		model.addAttribute("ratings", ratingService.findAllRatings());
		return "redirect:/rating/list";
	}
}
