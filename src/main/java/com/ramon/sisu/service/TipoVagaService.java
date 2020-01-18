package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.repository.TipoVagaRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class TipoVagaService {

	@Autowired
	private TipoVagaRepository repository;
	
	public TipoVaga criarTipoVaga(TipoVaga tipoVaga) {
		// TODO Auto-generated method stub
		return repository.save(tipoVaga);
	}

	public List<TipoVaga> buscarTipoVagas() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public List<TipoVaga> criarListaTipoVaga(List<TipoVaga> tipoVagas) {
		// TODO Auto-generated method stub
		return repository.saveAll(tipoVagas);
	}

	public TipoVaga findBySigla(String sigla) {
		Optional<TipoVaga> tipoVagaOpt = repository.findBySigla(sigla);
		
		if(tipoVagaOpt.isPresent()) {
			return tipoVagaOpt.get();
		}
		
		throw new ObjectNotFoundException("nao foi encontrado tipo vaga com essa sigla : " + sigla);
	}

	public TipoVaga findById(Integer valor) {
		Optional<TipoVaga> tipoVagaOpt = repository.findById(valor);
		
		if(tipoVagaOpt.isPresent()) {
			return tipoVagaOpt.get();
		}
	
		throw new ObjectNotFoundException("nao foi encontrado tipo vaga com esse id : " + valor);
	}

	public TipoVaga findByDescricao(TipoVaga tipoVaga) {
		Optional<TipoVaga> tipoVagaOpt = repository.findByDescricao(tipoVaga.getDescricao());
		
		if(tipoVagaOpt.isPresent()) {
			return tipoVagaOpt.get();
		}
		tipoVaga.setSigla("lixo");
		tipoVaga.setResumo("lixo");
		
		return criarTipoVaga(tipoVaga);
	}
	

	

	
}
