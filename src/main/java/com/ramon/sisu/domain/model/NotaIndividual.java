package com.ramon.sisu.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class NotaIndividual {

	private Curso curso;
	
	private Faculdade faculdade;
	
	private TipoVaga tipoVaga;
	
	private double notaRedacao;
	
	private double notaNatureza;
	
	private double notaHumanas;
	
	private double notaLinguagens;
	
	private double notaMatematica;
	
	private double mediaNormal;
	
	private List<Media> medias;
}
