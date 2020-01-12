package com.ramon.sisu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Media;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.domain.model.Vaga;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class NotaIndividualService {

	@Autowired
	public CursoService cursoService;
	

	@Autowired
	public FaculdadeService faculdadeService;

	@Autowired
	public CursoFaculdadeService cursoFaculdadeService;

	@Autowired
	public TipoVagaService tipoVagaService;
	
	public NotaIndividual buscarNotas(NotaIndividual notaIndividual) {
		List<CursoFaculdade> cursosFaculdade = buscarListaDeFaculdades(notaIndividual);
		
		notaIndividual.setMediaNormal(calcularMediaNormal(notaIndividual));
		List<Media> medias = cursosFaculdade.stream().map( x -> new Media(x.getVagas().get(0), calcularMedia(x, notaIndividual))).collect(Collectors.toList());
		notaIndividual.setMedias(medias);
		
		return notaIndividual;
	}

	
	
	private double calcularMediaNormal(NotaIndividual notaIndividual) {
		double media = 	notaIndividual.getNotaHumanas()
				+ notaIndividual.getNotaRedacao()
				+ notaIndividual.getNotaNatureza()
				+ notaIndividual.getNotaLinguagens()
				+ notaIndividual.getNotaMatematica();
		return media/5;
	}



	private double calcularMedia(CursoFaculdade cursoFaculdade, NotaIndividual notaIndividual) {
		
		double media = 	cursoFaculdade.getHumanaPeso()*notaIndividual.getNotaHumanas()
						+ cursoFaculdade.getRedacaoPeso()*notaIndividual.getNotaRedacao()
						+ cursoFaculdade.getNaturezaPeso()*notaIndividual.getNotaNatureza()
						+ cursoFaculdade.getLinguagemPeso()*notaIndividual.getNotaLinguagens()
						+ cursoFaculdade.getMatematicaPeso()*notaIndividual.getNotaMatematica();
		
		media = media / (cursoFaculdade.sumPesos());
		return media;
	}



	public List<CursoFaculdade> buscarListaDeFaculdades(NotaIndividual notaIndividual) {
		
	try {
		CursoFaculdade cursoFaculdadeExample = new CursoFaculdade();
		
		carregarCurso(notaIndividual, cursoFaculdadeExample);
		carregarFaculdade(notaIndividual, cursoFaculdadeExample);
		carregarTipoVaga(notaIndividual, cursoFaculdadeExample);
	
		
		return cursoFaculdadeService.findAll(cursoFaculdadeExample);
	} catch (Exception e) {
		 throw new ObjectNotFoundException(e.getMessage());
	}
	
	}

	private void carregarCurso(NotaIndividual notaIndividual, CursoFaculdade cursoFaculdadeExample) {
		if(notaIndividual.getCurso() != null) {
			cursoFaculdadeExample.setCurso(cursoService
											.findById(notaIndividual.getCurso().getId()));
		}
	}

	private void carregarFaculdade(NotaIndividual notaIndividual, CursoFaculdade cursoFaculdadeExample) {
		if(notaIndividual.getFaculdade() != null) {
			cursoFaculdadeExample.setCampus(Campus.builder()
					.faculdade(faculdadeService.findById(notaIndividual.getFaculdade().getId()))
					.build());
		
		}
	}

	private void carregarTipoVaga(NotaIndividual notaIndividual, CursoFaculdade cursoFaculdadeExample) {
		if(notaIndividual.getTipoVaga() != null) {
				cursoFaculdadeExample.setVagas(Arrays.asList(Vaga.builder()
						.tipoVaga(tipoVagaService.findById(notaIndividual.getTipoVaga().getId()))
						.build()));
		}
	}

}
