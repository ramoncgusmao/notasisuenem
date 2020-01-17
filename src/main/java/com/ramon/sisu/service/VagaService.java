package com.ramon.sisu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Vaga;

@Service
public class VagaService {

	@Autowired
	public TipoVagaService tipoVagaService;
	
	public List<Vaga> validaLista(List<Vaga> vagas, CursoFaculdade cursoFaculdade) {
		// TODO Auto-generated method stub
		return vagas.stream().map(x -> validarVaga(x, cursoFaculdade)).collect(Collectors.toList());
	}

	public Vaga validarVaga(Vaga vaga, CursoFaculdade cursoFaculdade) {
		vaga.setCursoFaculdade(cursoFaculdade);
		
		vaga.setTipoVaga(tipoVagaService.findByDescricao(vaga.getTipoVaga()));
		
		return vaga;
	}


}
