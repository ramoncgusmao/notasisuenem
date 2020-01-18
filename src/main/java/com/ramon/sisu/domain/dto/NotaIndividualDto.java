package com.ramon.sisu.domain.dto;

import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.Faculdade;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.service.exception.ObjectNotFoundException;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NotaIndividualDto {

	@NotNull
	private Integer curso;
	
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
		
		notaIndividual.setCurso(Curso.builder().id(curso).build());

		
		
		
		return notaIndividual;
		
	}
}
