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
		CursoFaculdade curFaculdade =  validarCursoFaculdade(cursoFaculdade);
		return repository.save(curFaculdade);
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
				.withIgnorePaths("naturezaPeso", "humanaPeso", "linguagemPeso", "matematicaPeso", "mediaMinima", "redacaoPeso", "possuiCotaRegional","porcentagemRegional"
						,"notaMinimaRedacao","notaMinimaNatureza","notaMinimaHumana","notaMinimaLinguagem","notaMinimaMatematica")
				.withIgnoreNullValues()
				);
		Optional<CursoFaculdade> cursoFaculdadeOpt = repository.findOne(example);
		if(cursoFaculdadeOpt.isPresent()) {
			
			return cursoFaculdadeOpt.get();
		}
		throw new ObjectNotFoundException("não existe curso faculdade com esses campos ");
	}
	
	public List<CursoFaculdade> criarCursoFaculdadeLista(List<CursoFaculdade> lista) {
		
		
		return lista.stream().map(curso -> criarCursoFaculdade(curso)).collect(Collectors.toList());
	}

	
	public CursoFaculdade validarCursoFaculdade(CursoFaculdade cursoFaculdade) {
		carregaCampus(cursoFaculdade);
		carregaPeriodo(cursoFaculdade);
		carregaCurso(cursoFaculdade);
		carregaListaVagas(cursoFaculdade);
		try {
			CursoFaculdade cursoFaculdadeNovo = findByOne(cursoFaculdade);
			long count = cursoFaculdade.getVagas().stream().filter(v -> v.getTipoVaga() == cursoFaculdade.getVagas().get(0).getTipoVaga()).count();
			if(count == 0) {
				cursoFaculdade.getVagas().add(cursoFaculdade.getVagas().get(0));
			}
			return cursoFaculdadeNovo;
		} catch (Exception e) {
			return cursoFaculdade;
		}
		
		
	}

	private void carregaListaVagas(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setVagas(vagaService.validaLista(cursoFaculdade.getVagas(), cursoFaculdade));
	}

	private void carregaCurso(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setCurso(cursoService.findByNome(cursoFaculdade.getCurso()));
	}

	private void carregaPeriodo(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setPeriodo(periodoService.findByNome(cursoFaculdade.getPeriodo().getNome()));
	}

	private void carregaCampus(CursoFaculdade cursoFaculdade) {
		cursoFaculdade.setCampus(campusService.findByNomeAndMunicipio(cursoFaculdade.getCampus()));
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
