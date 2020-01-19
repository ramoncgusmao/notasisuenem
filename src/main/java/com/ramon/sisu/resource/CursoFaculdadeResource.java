package com.ramon.sisu.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.CursoFaculdadeDto;
import com.ramon.sisu.domain.dto.CursoFaculdadeEditarDto;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.service.CursoFaculdadeService;


@RestController
@RequestMapping("/cursofaculdade")
public class CursoFaculdadeResource {

	@Autowired
	private CursoFaculdadeService service;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
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
			return ResponseEntity.ok(cursoFaculdades);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/criarlista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarListaCursoFaculdade(@RequestBody @Valid List<CursoFaculdadeDto> lista) {
		
		try {
			List<CursoFaculdade> cursoFaculdadeList = lista.stream().map(dto -> dto.convertToEntity()).collect(Collectors.toList());
			cursoFaculdadeList = service.criarCursoFaculdadeLista(cursoFaculdadeList);
			return new ResponseEntity(cursoFaculdadeList, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity editarCursoFaculdade(@PathVariable Integer id, @RequestBody CursoFaculdadeEditarDto curso) {
		
		try {
			CursoFaculdade cursoFaculdade = curso.convertToEntity();
			 cursoFaculdade = service.editarCursoFaculdade(id, cursoFaculdade);
			return ResponseEntity.ok(cursoFaculdade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
