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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {
	// TODO: Inject RuleName service ==> OK
	@Autowired
	RuleNameService ruleNameService;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		// TODO: find all RuleName, add to model ==> OK
		List<RuleName> ruleNames = ruleNameService.findAllRuleNames();
		model.addAttribute("ruleNames", ruleNames);
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return RuleName list ==>
		// OK
		if (!result.hasErrors()) {
			RuleName rn = new RuleName();
			rn.setName(ruleName.getName());
			rn.setDescription(ruleName.getDescription());
			rn.setJson(ruleName.getJson());
			rn.setTemplate(ruleName.getTemplate());
			rn.setSqlStr(ruleName.getSqlStr());
			rn.setSqlPart(ruleName.getSqlPart());
			ruleNameService.save(rn);
			model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get RuleName by Id and to model then show to the form ==> OK
		RuleName ruleName = ruleNameService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update RuleName and ==>
		// OK
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		RuleName rn = ruleNameService.findById(id).get();
		rn.setName(ruleName.getName());
		rn.setDescription(ruleName.getDescription());
		rn.setJson(ruleName.getJson());
		rn.setTemplate(ruleName.getTemplate());
		rn.setSqlStr(ruleName.getSqlStr());
		rn.setSqlPart(ruleName.getSqlPart());
		ruleNameService.save(rn);
		model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		// TODO: Find RuleName by Id and delete the RuleName, return to Rule list ==> OK
		RuleName ruleName = ruleNameService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		ruleNameService.delete(ruleName);
		model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
		return "redirect:/ruleName/list";
	}
}
