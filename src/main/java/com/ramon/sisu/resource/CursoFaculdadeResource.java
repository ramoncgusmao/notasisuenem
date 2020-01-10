package com.ramon.sisu.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.CursoFaculdadeDto;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.service.CursoFaculdadeService;


@RestController
@RequestMapping("/cursofaculdade")
public class CursoFaculdadeResource {

	@Autowired
	private CursoFaculdadeService service;
	
	@PostMapping()
	public ResponseEntity criarCursoFaculdade(@RequestBody @Valid CursoFaculdadeDto dto) {
		
		try {
			CursoFaculdade cursoFaculdade = dto.convertToEntity();
			cursoFaculdade = service.criarCursoFaculdade(cursoFaculdade);
			return new ResponseEntity(cursoFaculdade, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity buscarCursoFaculdades() {
		
		try {
			List<CursoFaculdade> cursoFaculdades = service.buscarCursoFaculdades();
			return new ResponseEntity(cursoFaculdades, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
