package com.ramon.sisu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.dto.NotaDeCorteDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.Vaga;

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
		CursoFaculdade cursoFaculdade = buscarCursoFaculdade(dto, periodo);
		Dia dia = diaService.findByDiaAndPeriodo(dto.getDia(), periodo);
		
		List<NotaDeCorte> notasDeCorte = cursoFaculdade.getVagas().stream().map(vaga -> converterEmNotaDeCorte(dia, vaga, dto.getNotaDeCorteDto())).collect(Collectors.toList());
		return null;
	}

	private NotaDeCorte converterEmNotaDeCorte(Dia dia, Vaga vaga, List<NotaDeCorteDto> notaDeCorteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public CursoFaculdade buscarCursoFaculdade(DadosNotaDeCorteDto dto, Periodo periodo) {
		
		Curso curso = cursoService.findByNome(dto.getCurso());
		Campus campus = campusService.findByNome(dto.getCampus());
		CursoFaculdade cursoFaculdade = new CursoFaculdade();
		cursoFaculdade.setCampus(campus);
		cursoFaculdade.setCurso(curso);
		cursoFaculdade.setPeriodo(periodo);
		
		return cursoFaculdadeService.findByOne(cursoFaculdade);
	}

}
