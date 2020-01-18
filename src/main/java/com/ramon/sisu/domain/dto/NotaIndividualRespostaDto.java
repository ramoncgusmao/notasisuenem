package com.ramon.sisu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaIndividualRespostaDto {

	private String estado;
	
	private String Faculdade;
	
	private String campus;
	
	private String curso;
	
	private String nota;
	
	private double notaCotaRegional;
	
	private int vagas;
}
