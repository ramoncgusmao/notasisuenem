package com.ramon.sisu.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.Turno;
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
		CursoFaculdade cursoFaculdade = new CursoFaculdade();
			cursoFaculdade.setRedacaoPeso(rp);
			cursoFaculdade.setNaturezaPeso(np);
			cursoFaculdade.setHumanaPeso(hp);
			cursoFaculdade.setLinguagemPeso(lp);
			cursoFaculdade.setMatematicaPeso(mp);
			cursoFaculdade.setMediaMinima(mediaMinima);
			cursoFaculdade.setPossuiCotaRegional(possuiCotaRegional);
			cursoFaculdade.setPorcentagemRegional(porcentagemRegional);
			cursoFaculdade.setPeriodo(Periodo.builder().nome(periodo).build());
			cursoFaculdade.setCampus(Campus.builder().nome(campus).build());
			cursoFaculdade.setCurso(Curso.builder().nome(curso).build());
			cursoFaculdade.setVagas(vagas.stream().map(x -> x.convertToEntity()).collect(Collectors.toList()));
			cursoFaculdade.setTurno(Turno.toEnum(turno));
		
			return cursoFaculdade;
	}

}
