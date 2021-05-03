package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepository;

	public List<CurvePoint> findAllCurvePoints() {
		return curvePointRepository.findAll();
	}

	public Optional<CurvePoint> findById(int id) {
		return curvePointRepository.findById(id);
	}

	public CurvePoint save(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	public void delete(CurvePoint curvePoint) {
		curvePointRepository.delete(curvePoint);
	}
}
