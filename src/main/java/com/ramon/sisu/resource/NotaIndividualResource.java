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
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.NotaIndividualDto;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.service.NotaIndividualService;

@RestController
@RequestMapping("/notaindividual")
public class NotaIndividualResource {

	

	@Autowired
	private NotaIndividualService service;
	
	@PostMapping
	public ResponseEntity buscarNotaIndividual(@RequestBody @Valid NotaIndividualDto dto) {
		
		try {
			
			NotaIndividual notaIndividual = dto.convertToEntity();
			notaIndividual = service.buscarNotas(notaIndividual);
			return ResponseEntity.ok(notaIndividual);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
