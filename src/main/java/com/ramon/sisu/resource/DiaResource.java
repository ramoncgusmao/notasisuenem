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

import com.ramon.sisu.domain.dto.PeriodoDto;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.service.PeriodoService;

@RestController
@RequestMapping("/periodo")
public class DiaResource {

	@Autowired
	private PeriodoService service;
	@PostMapping
	public ResponseEntity criar(@RequestBody @Valid PeriodoDto dto) {
		
		try {
			Periodo periodo = dto.convertToEntity();
			periodo = service.save(periodo);
			return new ResponseEntity<Periodo>(periodo, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public ResponseEntity buscar() {
		
		try {
			List<Periodo> periodos = service.find();
			return ResponseEntity.ok(periodos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
