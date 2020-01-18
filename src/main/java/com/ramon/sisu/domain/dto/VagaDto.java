package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.domain.model.Vaga;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class VagaDto {

	@NotNull
	private int quantidade;

	@NotBlank
	private String tipoVaga;
	
	public Vaga convertToEntity() {
		// TODO Auto-generated method stub
		return Vaga.builder()
				.quantidade(quantidade)
				.tipoVaga(TipoVaga.builder().descricao(tipoVaga).build())
				.build();
	}
}
