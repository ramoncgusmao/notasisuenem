package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.dto.NotaDeCorteDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.Vaga;
import com.ramon.sisu.repository.NotaDeCorteRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
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
	
	@Autowired
	private NotaDeCorteRepository repository;
	public List<NotaDeCorte> criarNotaDeCorte(DadosNotaDeCorteDto dto) {
		
		Periodo periodo = periodoService.findByNome(dto.getPeriodo());
		CursoFaculdade cursoFaculdade = buscarCursoFaculdade(dto, periodo);
		Dia dia = diaService.findByDiaAndPeriodo(dto.getDia(), periodo);
		
		List<NotaDeCorte> notasDeCorte = cursoFaculdade.getVagas().stream().map(vaga -> converterEmNotaDeCorte(dia, vaga, dto.getNotaDeCorteDto())).collect(Collectors.toList());
	
		return repository.saveAll(notasDeCorte);
	}

	private NotaDeCorte converterEmNotaDeCorte(Dia dia, Vaga vaga, List<NotaDeCorteDto> notaDeCorteDto) {
		Optional<NotaDeCorteDto> notaOpt = notaDeCorteDto.stream().filter( x -> x.getTipoVaga().equals(vaga.getTipoVaga().getSigla())).findFirst();
		
		if(notaOpt.isPresent()) {
			return NotaDeCorte.builder().dia(dia).vaga(vaga).nota(notaOpt.get().getNota()).build();
		}
		
		throw new ObjectNotFoundException(" nao existe a nota para a vaga : " + vaga.getTipoVaga().getSigla());
		
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

	public List<NotaDeCorte> buscarNotaDeCortes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public List<List<NotaDeCorte>> criarNotaDeCorteLista(@Valid List<DadosNotaDeCorteDto> lista) {
		List<List<NotaDeCorte>> notasDeCorte = lista.stream().map(notaDeCorte -> criarNotaDeCorte(notaDeCorte)).collect(Collectors.toList());
		return notasDeCorte;
	}



}
