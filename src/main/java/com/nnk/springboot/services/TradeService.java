package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;

	public List<Trade> findAllTrades() {
		return tradeRepository.findAll();
	}

	public Optional<Trade> findById(int id) {
		return tradeRepository.findById(id);
	}

	public Trade save(Trade trade) {
		return tradeRepository.save(trade);
	}

	public void delete(Trade trade) {
		tradeRepository.delete(trade);
	}
}
