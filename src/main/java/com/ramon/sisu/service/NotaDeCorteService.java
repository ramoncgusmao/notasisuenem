package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.domain.model.Periodo;

public class NotaDeCorteService {

	@Autowired
	private CursoFaculdadeService cursoFaculdadeService;
	
	
	@Autowired
	private DiaService diaService;
	
	@Autowired
	private PeriodoService periodoService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CampusService campusService;
	
	public List<NotaDeCorte> criarNotaDeCorte(DadosNotaDeCorteDto dto) {
		
		Periodo periodo = periodoService.findByNome(dto.getPeriodo());
		Curso curso = cursoService.findByNome(dto.getCurso());
		Campus campus = campusService.findByNome(dto.getCampus());
		CursoFaculdade cursoFaculdade = new CursoFaculdade();
		cursoFaculdade.setCampus(campus);
		cursoFaculdade.setCurso(curso);
		cursoFaculdade.setPeriodo(periodo);
		
		cursoFaculdade = cursoFaculdadeService.findByOne(cursoFaculdade);
		
		
		
		return null;
	}

}
