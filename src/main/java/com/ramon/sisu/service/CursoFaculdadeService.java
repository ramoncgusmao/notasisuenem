package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.repository.CursoFaculdadeRepository;

@Service
public class CursoFaculdadeService {

	@Autowired
	private CursoFaculdadeRepository repository;
	
	public CursoFaculdade criarCursoFaculdade(CursoFaculdade cursoFaculdade) {
		// TODO Auto-generated method stub
		return repository.save(cursoFaculdade);
	}

	public List<CursoFaculdade> buscarCursoFaculdades() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
