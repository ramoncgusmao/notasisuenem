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

import com.ramon.sisu.domain.dto.FaculdadeDto;
import com.ramon.sisu.domain.model.Faculdade;
import com.ramon.sisu.service.FaculdadeService;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeResource {

	@Autowired
	private FaculdadeService service;
	@PostMapping
	public ResponseEntity criarFaculdade(@RequestBody @Valid FaculdadeDto dto) {
		
		try {
			Faculdade faculdade = dto.convertToEntity();
			faculdade = service.save(faculdade);
			return new ResponseEntity<Faculdade>(faculdade, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public ResponseEntity buscarFaculdade() {
		
		try {
			List<Faculdade> faculdades = service.find(null);
			return ResponseEntity.ok(faculdades);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
