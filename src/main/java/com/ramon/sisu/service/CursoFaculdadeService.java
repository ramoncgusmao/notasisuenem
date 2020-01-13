 package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.domain.model.Vaga;
import com.ramon.sisu.repository.CursoFaculdadeRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

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
	
	public CursoFaculdade findByOne(CursoFaculdade cursoFaculdadeExample) {
		// TODO Auto-generated method stub
		Example example = Example.of(cursoFaculdadeExample, ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withIgnorePaths("naturezaPeso", "humanaPeso", "linguagemPeso", "matematicaPeso", "mediaMinima", "redacaoPeso", "possuiCotaRegional","porcentagemRegional")
				.withIgnoreNullValues()
				);
		Optional<CursoFaculdade> cursoFaculdadeOpt = repository.findOne(example);
		if(cursoFaculdadeOpt.isPresent()) {
			
			return cursoFaculdadeOpt.get();
		}
		throw new ObjectNotFoundException("não existe curso faculdade com esses campos ");
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
		List<CursoFaculdade> lista = repository.findAll(example);
		
		if(cursoFaculdadeExample.getVagas().size() > 0) {
			lista.stream().forEach(x -> x.setVagas(retirarExcedente(x.getVagas(), cursoFaculdadeExample.getVagas().get(0).getTipoVaga())));
		}
		return lista;
	}
		
	private List<Vaga> retirarExcedente(List<Vaga> vagas, TipoVaga vaga){
		return vagas.stream().filter(x -> x.getTipoVaga().getId() == vaga.getId()).collect(Collectors.toList());
	}
	

}
