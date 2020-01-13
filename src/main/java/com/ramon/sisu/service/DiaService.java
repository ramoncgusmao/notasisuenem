package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.repository.DiaRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class DiaService {

	@Autowired
	private DiaRepository repository;
	
	@Autowired
	private PeriodoService periodoService;
	public Dia save(Dia dia) {
		carregarPeriodo(dia);
		
		return repository.save(dia);
	}
	private void carregarPeriodo(Dia dia) {
		dia.setPeriodo(periodoService.findByNome(dia.getPeriodo().getNome()));
	}
	public List<Dia> find(Dia diaExample) {
		
		carregarPeriodo(diaExample);
		System.out.println(diaExample);
		Example example = Example.of(diaExample, ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withIgnoreNullValues()
				);
		return repository.findAll(example);

	}
	public Dia findByDiaAndPeriodo(Integer dia, Periodo periodo) {
		
		Optional<Dia> diaOpt = repository.findByDiaAndPeriodo(dia, periodo); 
		
		if(diaOpt.isPresent()) {
			return diaOpt.get();
		}
		
		throw new ObjectNotFoundException("não foi encontrado dia " + dia);
	}

}
