package com.ramon.sisu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {

	private CursoFaculdade cursoFaculdade;
	
	private double nota;
	
}
