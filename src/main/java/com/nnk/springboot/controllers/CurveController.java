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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurveController {
	// TODO: Inject Curve Point service ==> OK
	@Autowired
	CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		// TODO: find all Curve Point, add to model ==> OK
		List<CurvePoint> curvePoints = curvePointService.findAllCurvePoints();
		model.addAttribute("curvePoints", curvePoints);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint curvePoint) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Curve list ==> OK
		if (!result.hasErrors()) {
			CurvePoint cp = new CurvePoint();
			cp.setCurveId(curvePoint.getCurveId());
			cp.setTerm(curvePoint.getTerm());
			cp.setValue(curvePoint.getValue());
			curvePointService.save(cp);
			model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get CurvePoint by Id and to model then show to the form ==> OK
		CurvePoint curvePoint = curvePointService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Curve and OK
		if (result.hasErrors()) {
			return "curvePoint/update";
		}
		CurvePoint cp = curvePointService.findById(id).get();
		cp.setCurveId(curvePoint.getCurveId());
		cp.setTerm(curvePoint.getTerm());
		cp.setValue(curvePoint.getValue());
		curvePointService.save(cp);
		model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Curve by Id and delete the Curve, return to Curve list ==> OK
		CurvePoint curvePoint = curvePointService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		curvePointService.delete(curvePoint);
		model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
		return "redirect:/curvePoint/list";
	}
}
