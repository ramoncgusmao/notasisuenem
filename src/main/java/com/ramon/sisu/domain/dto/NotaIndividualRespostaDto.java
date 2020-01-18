package com.ramon.sisu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaIndividualRespostaDto {

	private Integer id;
	
	private String estado;
	
	private String Faculdade;
	
	private String campus;
	
	private String curso;
	
	private String nota;
	
	private String notaCotaRegional;
	
	private int vagas;
	
	@JsonIgnore
	private Double notaOrder;
	
	private int vagaAmplaConcorrencia;
	
	private String cidade;
	
	
}
