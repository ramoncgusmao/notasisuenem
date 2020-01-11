package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.repository.PeriodoRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

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

	public Periodo findByNome(String nome) {
		Optional<Periodo> periodoOpt = repository.findByNome(nome);
		
		if(periodoOpt.isPresent()) {
			return periodoOpt.get();
		}
		
		throw new ObjectNotFoundException("nao foi encontrado periodo " + nome);
	}


}
