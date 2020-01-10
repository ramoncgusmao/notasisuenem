package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;
	
	public Curso criarCurso(Curso curso) {
		
		if(validarCurso(curso)) {
			throw new DataIntegrityViolationException("curso ja foi criado");
		}
		return repository.save(curso);
	}

	private boolean validarCurso(Curso curso) {
		return repository.existsByNome(curso.getNome());
	}

	public List<Curso> buscarCursos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
