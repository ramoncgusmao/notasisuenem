package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.repository.TipoVagaRepository;

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
	

	

	
}
