package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	BidListRepository bidListRepository;

	public List<BidList> findAllBids() {
		return bidListRepository.findAll();
	}

	public Optional<BidList> findById(int id) {
		return bidListRepository.findById(id);
	}

	public BidList save(BidList bidList) {
		return bidListRepository.save(bidList);
	}

	public void delete(BidList bidList) {
		bidListRepository.delete(bidList);
	}

}
