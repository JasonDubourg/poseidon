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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@Controller
public class BidListController {
	// TODO: Inject Bid service ==> OK
	@Autowired
	BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		// TODO: call service find all bids to show to the view ==> OK
		List<BidList> bidsList = bidListService.findAllBids();
		model.addAttribute("bids", bidsList);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(Model model) {
		BidList bidList = new BidList();
		model.addAttribute(bidList);
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bidList, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return bid list ==> OK
		if (!result.hasErrors()) {
			BidList bid = new BidList();
			bid.setAccount(bidList.getAccount());
			bid.setType(bidList.getType());
			bid.setBidQuantity(bidList.getBidQuantity());
			bidListService.save(bid);
			model.addAttribute("bids", bidListService.findAllBids());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Bid by Id and to model then show to the form ==> OK
		BidList bidList = bidListService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Bid and OK
		if (result.hasErrors()) {
			return "bidList/update";
		}
		BidList bid = bidListService.findById(id).get();
		bid.setAccount(bidList.getAccount());
		bid.setType(bidList.getType());
		bid.setBidQuantity(bidList.getBidQuantity());
		bidListService.save(bid);
		model.addAttribute("bids", bidListService.findAllBids());
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Bid by Id and delete the bid, return to Bid list ==> OK
		BidList bid = bidListService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		bidListService.delete(bid);
		model.addAttribute("bids", bidListService.findAllBids());
		return "redirect:/bidList/list";
	}
}
