package com.ramon.sisu.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Periodo;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class DiaDto {

	
	@NotNull
	private Integer dia;
	
	@NotNull
	private Date data;
	
	@NotBlank
	private String periodo;

	public Dia convertToEntity() {
		// TODO Auto-generated method stub
		return Dia.builder()
				.dia(dia)
				.data(data)
				.periodo(Periodo.builder().nome(periodo).build())
				.build();
	}
}
