package com.ramon.sisu.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	public ResponseEntity buscarFaculdade(@RequestParam(value = "nome", defaultValue = "") String nome) {
		
		try {
			List<Faculdade> faculdades = service.find(nome);
			return ResponseEntity.ok(faculdades);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/criarLista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarFaculdadeLista(@RequestBody @Valid List<FaculdadeDto> lista) {
		
		try {
			List<Faculdade> faculdades = lista.stream().map(x -> x.convertToEntity()).collect(Collectors.toList());
			faculdades = service.saveList(faculdades);
			return new ResponseEntity<List<Faculdade>>(faculdades, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
