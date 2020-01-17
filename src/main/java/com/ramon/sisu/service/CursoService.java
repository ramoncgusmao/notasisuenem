package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.repository.CursoRepository;
import com.ramon.sisu.service.exception.DataIntegrityException;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;
	
	public Curso criarCurso(Curso curso) {
		
		if(validarCurso(curso)) {
			throw new DataIntegrityViolationException("curso ja foi criado");
		}
		
		curso.setNome(curso.getNome().toUpperCase());
		return repository.save(curso);
	}

	private boolean validarCurso(Curso curso) {
		return repository.existsByNome(curso.getNome().toUpperCase());
	}

	public List<Curso> buscarCursos(String nome) {
		Curso cursoExample = Curso.builder().nome(nome).build();
		Example example = Example.of(cursoExample, ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withIgnorePaths("id")
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}

	public List<Curso> criarCursoLista(List<Curso> cursos) {
		// TODO Auto-generated method stub
		return repository.saveAll(cursos);
	}

	public Curso findByNome(Curso curso) {
		Optional<Curso> cursoOpt = repository.findByNome(curso.getNome().toUpperCase());
		
		if(cursoOpt.isPresent()) {
			return cursoOpt.get();
		}
		
		return criarCurso(curso);
	}

	public Curso findByNome(String curso) {
		Optional<Curso> cursoOpt = repository.findByNome(curso.toUpperCase());
		
		if(cursoOpt.isPresent()) {
			return cursoOpt.get();
		}
		
		throw new DataIntegrityException("nao existe esse curso");
	}
	public Curso findById(Integer id) {
	Optional<Curso> cursoOpt = repository.findById(id);
		
		if(cursoOpt.isPresent()) {
			return cursoOpt.get();
		}
		
		throw new ObjectNotFoundException("nao foi encontrado curso com o id " + id);

	}

}
