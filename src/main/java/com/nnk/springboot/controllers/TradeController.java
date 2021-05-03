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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {
	// TODO: Inject Trade service ==> OK
	@Autowired
	TradeService tradeService;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		// TODO: find all Trade, add to model ==> OK
		List<Trade> trades = tradeService.findAllTrades();
		model.addAttribute("trades", trades);
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade trade) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Trade list ==> OK
		if (!result.hasErrors()) {
			Trade tr = new Trade();
			tr.setAccount(trade.getAccount());
			tr.setType(trade.getType());
			tr.setBuyQuantity(trade.getBuyQuantity());
			tradeService.save(tr);
			model.addAttribute("trades", tradeService.findAllTrades());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Trade by Id and to model then show to the form ==> OK
		Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Trade and OK
		if (result.hasErrors()) {
			return "/trade/update";
		}
		Trade tr = tradeService.findById(id).get();
		tr.setAccount(trade.getAccount());
		tr.setType(trade.getType());
		tr.setBuyQuantity(trade.getBuyQuantity());
		tradeService.save(tr);
		model.addAttribute("trades", tradeService.findAllTrades());
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Trade by Id and delete the Trade, return to Trade list ==> OK
		Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		tradeService.delete(trade);
		model.addAttribute("trades", tradeService.findAllTrades());
		return "redirect:/trade/list";
	}
}
