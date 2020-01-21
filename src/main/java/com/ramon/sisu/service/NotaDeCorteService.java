package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Faculdade;
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
	public NotaDeCorte criarNotaDeCorte(DadosNotaDeCorteDto dto) {
		
		Periodo periodo = periodoService.findByNome(dto.getPeriodo());
		CursoFaculdade cursoFaculdade = buscarCursoFaculdade(dto, periodo);
		Dia dia = diaService.findByDiaAndPeriodo(dto.getDia(), periodo);
		Optional<Vaga> vagaOpt = cursoFaculdade.getVagas().stream().filter(vaga -> vaga.getTipoVaga().getSigla().equals(dto.getSiglaTipoVaga()) && vaga.getQuantidade() > 0).findFirst();
		
		if(!vagaOpt.isPresent()) {
			throw new ObjectNotFoundException("não foi encontrado vaga com esses parametros" + dto.getCampus() + " " + dto.getCurso());
		}
		
		NotaDeCorte notasDeCorte = converterEmNotaDeCorte(dia, vagaOpt.get(), dto.getNotaDeCorte());
	
		return repository.save(notasDeCorte);
	}

	private NotaDeCorte converterEmNotaDeCorte(Dia dia, Vaga vaga, double notaDeCorte) {
			return NotaDeCorte.builder().dia(dia).vaga(vaga).nota(notaDeCorte).build();
	}

	public CursoFaculdade buscarCursoFaculdade(DadosNotaDeCorteDto dto, Periodo periodo) {
		try {
			Curso curso = cursoService.findByNome(dto.getCurso());
			Campus campus = campusService.findByNomeAndMunicipio(Campus.builder()
									.nome(dto.getCampus())
									.faculdade(Faculdade.builder().sigla(dto.getSiglaFaculdade()).build())
									.build());
			CursoFaculdade cursoFaculdade = new CursoFaculdade();
			cursoFaculdade.setCampus(campus);
			cursoFaculdade.setCurso(curso);
			cursoFaculdade.setPeriodo(periodo);
			
			return cursoFaculdadeService.findByOne(cursoFaculdade);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ObjectNotFoundException(e.getMessage());
		}
	
	}

	public List<NotaDeCorte> buscarNotaDeCortes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public List<NotaDeCorte> criarNotaDeCorteLista( List<DadosNotaDeCorteDto> lista) {
		List<NotaDeCorte> notasDeCorte = lista.stream().map(notaDeCorte -> criarNotaDeCorte(notaDeCorte)).collect(Collectors.toList());
		return notasDeCorte;
	}



}
