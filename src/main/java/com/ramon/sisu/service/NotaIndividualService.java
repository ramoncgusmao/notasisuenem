package com.ramon.sisu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.domain.model.Vaga;

@Service
public class NotaIndividualService {

	@Autowired
	public CursoService cursoService;
	

	@Autowired
	public FaculdadeService faculdadeService;

	@Autowired
	public CursoFaculdadeService cursoFaculdadeService;
	
	public TipoVagaService tipoVagaService;
	
	public NotaIndividual buscarNotas(NotaIndividual notaIndividual) {
		return notaIndividual;
	}

	public List<CursoFaculdade> buscarListaDeFaculdades(NotaIndividual notaIndividual) {
		
		CursoFaculdade cursoFaculdadeExample = new CursoFaculdade();
		
		if(notaIndividual.getCurso() != null) {
			cursoFaculdadeExample.setCurso(cursoService.findById(notaIndividual.getCurso().getId()));
		}
		if(notaIndividual.getFaculdade() != null) {
			cursoFaculdadeExample.setCampus(Campus.builder().faculdade(faculdadeService.findById(notaIndividual.getFaculdade().getId())).build());
		
		}
		if(notaIndividual.getTipoVaga() != null) {

			cursoFaculdadeExample.setVagas(Arrays.asList(Vaga.builder()
					.tipoVaga(tipoVagaService.findById(notaIndividual.getTipoVaga().getId()))
					.build()));
	
			System.out.println("problema aqui");
		}
		System.out.println(cursoFaculdadeExample);
		
		
		return cursoFaculdadeService.findAll(cursoFaculdadeExample);
	}

}
