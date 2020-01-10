package com.ramon.sisu.domain.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.TipoVaga;

import lombok.Data;

@Data
public class TipoVagaDto {

	@NotBlank
	private String resumo;
	
	@NotBlank
	private String sigla;
	
	private String descricao;
	
	public TipoVaga convertToEntity() {
		return TipoVaga.builder()
				.descricao(descricao)
				.sigla(sigla)
				.resumo(resumo)
				.build();
	}
}
