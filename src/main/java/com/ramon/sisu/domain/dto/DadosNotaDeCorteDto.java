package com.ramon.sisu.domain.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.Vaga;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class DadosNotaDeCorteDto {


	@NotNull
	private Integer dia;
	
	@NotBlank
	private String periodo;
	
	@NotBlank
	private String curso;
	
	@NotBlank
	private String campus;
	
	@NotNull
	List<NotaDeCorteDto> notaDeCorteDto;
}
