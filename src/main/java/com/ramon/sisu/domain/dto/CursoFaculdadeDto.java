package com.ramon.sisu.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.Turno;
import com.ramon.sisu.domain.model.Vaga;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CursoFaculdadeDto {

	@NotNull
	private double rp;
	
	@NotNull
	private double np;
	
	@NotNull
	private double hp;
	
	@NotNull
	private double lp;
	
	@NotNull
	private double mp;
	
	
	private double mediaMinima;
	
	private boolean possuiCotaRegional;
	
	private double porcentagemRegional;
	
	@NotBlank
	private String periodo;

	@NotBlank
	private String campus;
	
	@NotBlank
	private String curso;
	
	@NotNull
	private Integer turno;
	
	@NotNull
	private List<VagaDto> vagas = new ArrayList<>();
	
	
	public CursoFaculdade convertToEntity() {
		// TODO Auto-generated method stub
		return CursoFaculdade.builder()
				.redacaoPeso(rp)
				.naturezaPeso(np)
				.humanaPeso(hp)
				.linguagemPeso(lp)
				.matematicaPeso(mp)
				.mediaMinima(mediaMinima)
				.possuiCotaRegional(possuiCotaRegional)
				.porcentagemRegional(porcentagemRegional)
				.periodo(Periodo.builder().nome(periodo).build())
				.campus(Campus.builder().nome(campus).build())
				.curso(Curso.builder().nome(curso).build())
				.vagas(vagas.stream().map(x -> x.convertToEntity()).collect(Collectors.toList()))
				.turno(Turno.toEnum(turno))
				.build();
	}

}
