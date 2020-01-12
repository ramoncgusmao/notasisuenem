 package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.repository.CursoFaculdadeRepository;

@Service
public class CursoFaculdadeService {

	@Autowired
	private CursoFaculdadeRepository repository;
	
	@Autowired
	private CampusService campusService;
	
	@Autowired
	private PeriodoService periodoService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private VagaService vagaService;

	
	public CursoFaculdade criarCursoFaculdade(CursoFaculdade cursoFaculdade) {
		validarCursoFaculdade(cursoFaculdade);
		return repository.save(cursoFaculdade);
	}


	public List<CursoFaculdade> buscarCursoFaculdades() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	
	public List<CursoFaculdade> criarCursoFaculdadeLista(List<CursoFaculdade> lista) {
		
		lista.forEach(cursoFaculdade -> validarCursoFaculdade(cursoFaculdade));
		return repository.saveAll(lista);
	}

	
	public void validarCursoFaculdade(CursoFaculdade cursoFaculdade) {
		carregaCampus(cursoFaculdade);
		carregaPeriodo(cursoFaculdade);
		carregaCurso(cursoFaculdade);
		carregaListaVagas(cursoFaculdade);
		
	}

	private void carregaListaVagas(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setVagas(vagaService.validaLista(cursoFaculdade.getVagas(), cursoFaculdade));
	}

	private void carregaCurso(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setCurso(cursoService.findByNome(cursoFaculdade.getCurso().getNome()));
	}

	private void carregaPeriodo(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setPeriodo(periodoService.findByNome(cursoFaculdade.getPeriodo().getNome()));
	}

	private void carregaCampus(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setCampus(campusService.findByNome(cursoFaculdade.getCampus().getNome()));
	}


	public List<CursoFaculdade> findAll(CursoFaculdade cursoFaculdadeExample) {
		Example example = Example.of(cursoFaculdadeExample, ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withIgnorePaths("naturezaPeso", "humanaPeso", "linguagemPeso", "matematicaPeso", "mediaMinima", "redacaoPeso", "possuiCotaRegional","porcentagemRegional")
				.withIgnoreNullValues()
				);
		
		return repository.findAll(example);
	}
		
	

}
