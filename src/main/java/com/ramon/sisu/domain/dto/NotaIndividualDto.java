package com.ramon.sisu.domain.dto;

import javax.management.loading.PrivateClassLoader;

import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.Faculdade;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.service.exception.ObjectNotFoundException;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NotaIndividualDto {

	private Integer curso;
	
	private Integer faculdade;
	
	private Integer tipoVaga;
	
	@NotNull
	private double notaRedacao;
	@NotNull
	private double notaNatureza;
	@NotNull
	private double notaHumanas;
	@NotNull
	private double notaLinguagens;
	@NotNull
	private double notaMatematica;

	public NotaIndividual convertToEntity() {
		boolean flagFiltro = false;
		
		NotaIndividual notaIndividual = new NotaIndividual();
		notaIndividual.setNotaRedacao(notaRedacao);
		notaIndividual.setNotaNatureza(notaNatureza);
		notaIndividual.setNotaHumanas(notaHumanas);
		notaIndividual.setNotaHumanas(notaHumanas);
		notaIndividual.setNotaLinguagens(notaLinguagens);
		notaIndividual.setNotaMatematica(notaMatematica);
		
		if(curso != null) {
			notaIndividual.setCurso(Curso.builder().id(curso).build());
			flagFiltro = true;
		}
		
		if(faculdade != null) {
			notaIndividual.setFaculdade(Faculdade.builder().id(faculdade).build());
			flagFiltro = true;
		}
		
		if(tipoVaga != null) {
			notaIndividual.setTipoVaga(TipoVaga.builder().id(tipoVaga).build());
			flagFiltro = true;
		}
		
		if(!flagFiltro) {
			throw new ObjectNotFoundException("não possui nenhum dos parametros de filtro");
		}
		System.out.println(notaIndividual);
		return notaIndividual;
		
	}
}
