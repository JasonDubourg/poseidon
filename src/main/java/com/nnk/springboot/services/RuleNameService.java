package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

	@Autowired
	RuleNameRepository ruleNameRepository;

	public List<RuleName> findAllRuleNames() {
		return ruleNameRepository.findAll();
	}

	public Optional<RuleName> findById(int id) {
		return ruleNameRepository.findById(id);
	}

	public RuleName save(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	public void delete(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);
	}
}
