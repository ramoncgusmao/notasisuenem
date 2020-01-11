package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.repository.PeriodoRepository;

@Service
public class PeriodoService {

	@Autowired
	public PeriodoRepository repository;
	
	public Periodo save(Periodo periodo) {
		
		
		return repository.save(periodo);
	}

	public List<Periodo> find() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
