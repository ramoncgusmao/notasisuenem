package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NotaDeCorteDto {


	@NotNull
	private double nota;
	
	@NotBlank
	private String tipoVaga;
	
}
