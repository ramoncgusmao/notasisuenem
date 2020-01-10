package com.ramon.sisu.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.Periodo;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class PeriodoDto {

	@NotBlank
	private String nome;
	
	@NotNull
	private Integer ano;
	
	@NotNull
	private Date dataInicio;
	
	@NotNull
	private Date dataFim;

	public Periodo convertToEntity() {
		return Periodo.builder()
				.ano(ano)
				.dataInicio(dataInicio)
				.dataFim(dataFim)
				.nome(nome)
				.build();
		
	}
}
