package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Faculdade;

import lombok.Data;

@Data
public class CampusDto {
	
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String municipio;
	
	@NotBlank
	private String faculdade;
	
	
	
	
	public Campus convertToEntity() {
		// TODO Auto-generated method stub
		return Campus.builder()
				.nome(nome)
				.municipio(municipio)
				.faculdade(Faculdade.builder().sigla(faculdade).build())
				.build();
	}

}
