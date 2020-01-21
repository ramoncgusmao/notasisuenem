package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class DadosNotaDeCorteDto {


	@NotNull
	private Integer dia;
	
	private String periodo = "2020.1";
	
	@NotBlank
	private String curso;
	
	@NotBlank
	private String campus;
	
	@NotBlank
	private String siglaFaculdade;
	
	@NotNull
	private double notaDeCorte;
	
	private String municipio; 
	
	private String siglaTipoVaga = "A0";
}
