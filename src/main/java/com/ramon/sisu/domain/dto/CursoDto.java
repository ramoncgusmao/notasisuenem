package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ramon.sisu.domain.model.Curso;

import lombok.Data;

@Data
public class CursoDto {

	@NotEmpty
	@Size(min = 5)
	private String nome;
	
	public Curso convertToEntity() {
		return Curso.builder().nome(nome).build();
	}
}
