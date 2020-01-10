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

import com.ramon.sisu.domain.dto.CursoDto;
import com.ramon.sisu.domain.model.Curso;
import com.ramon.sisu.service.CursoService;


@RestController
@RequestMapping("/curso")
public class CursoResource {

	@Autowired
	private CursoService service;
	
	@PostMapping()
	public ResponseEntity criarCurso(@RequestBody @Valid CursoDto dto) {
		
		try {
			Curso curso = dto.convertToEntity();
			curso = service.criarCurso(curso);
			return new ResponseEntity(curso, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity buscarCursos() {
		
		try {
			List<Curso> cursos = service.buscarCursos();
			return new ResponseEntity(cursos, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
