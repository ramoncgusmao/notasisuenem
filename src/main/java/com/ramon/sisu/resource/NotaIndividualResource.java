package com.ramon.sisu.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.NotaIndividualDto;
import com.ramon.sisu.domain.dto.NotaIndividualRespostaDto;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.service.NotaIndividualService;

@RestController
@RequestMapping("/mediacurso")
public class NotaIndividualResource {

	

	@Autowired
	private NotaIndividualService service;
	
	@GetMapping
	public ResponseEntity buscarNotaIndividual(@RequestParam(value= "curso", required = true) int curso, @RequestParam(value= "notahumanas", required = true) double notaHumanas,
			@RequestParam(value= "notamatematica", required = true) double notaMatematica,
			@RequestParam(value= "notalinguagens", required = true) double notaLinguagens,
			@RequestParam(value= "notanatureza", required = true) double notaNatureza,
			@RequestParam(value= "notaredacao", required = true) double notaRedacao) {
		
		try {
			
			NotaIndividual notaIndividual = carregarNotaIndividual(curso, notaHumanas, notaMatematica, notaLinguagens,
					notaNatureza, notaRedacao);
					
					
					
			List<NotaIndividualRespostaDto >notaIndividualRespostaDtos = service.buscarNotas(notaIndividual);
			return ResponseEntity.ok(notaIndividualRespostaDtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private NotaIndividual carregarNotaIndividual(int curso, double notaHumanas, double notaMatematica,
			double notaLinguagens, double notaNatureza, double notaRedacao) {
		NotaIndividual notaIndividual = new NotaIndividual();
		notaIndividual.setNotaHumanas(notaHumanas);
		notaIndividual.setNotaLinguagens(notaLinguagens);
		notaIndividual.setNotaMatematica(notaMatematica);
		notaIndividual.setNotaNatureza(notaNatureza);
		notaIndividual.setNotaRedacao(notaRedacao);
		notaIndividual.setCurso(Curso.builder().id(curso).build());
		return notaIndividual;
	}
	
	@PostMapping("/buscarfaculdade")
	public ResponseEntity buscarFaculdadeTeste(@RequestBody @Valid NotaIndividualDto dto) {
		
		try {
			
			NotaIndividual notaIndividual = dto.convertToEntity();
			List<CursoFaculdade> cursosFaculdade = service.buscarListaDeFaculdades(notaIndividual);
			return ResponseEntity.ok(cursosFaculdade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
