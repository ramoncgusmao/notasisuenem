package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotEmpty;

import com.ramon.sisu.domain.model.Estado;
import com.ramon.sisu.domain.model.Faculdade;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class FaculdadeDto {


	@NotEmpty
	private String nome;
	@NotEmpty
	private String sigla;
	
	@NotNull
	private Integer estado;
	
	public Faculdade convertToEntity() {
		return Faculdade.builder()
				.nome(nome)
				.estado(Estado.builder().id(estado).build())
				.sigla(sigla)
				.build();
	}
}
