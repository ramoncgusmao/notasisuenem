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
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.CampusDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.service.CampusService;


@RestController
@RequestMapping("/campus")
public class CampusResource {

	@Autowired
	private CampusService service;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarCampus(@RequestBody @Valid CampusDto dto) {
		
		try {
			Campus campus = dto.convertToEntity();
			campus = service.criarCampus(campus);
			return new ResponseEntity(campus, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity buscarCampuss() {
		
		try {
			List<Campus> listaCampus = service.buscarCampuss();
			return ResponseEntity.ok(listaCampus);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/criarlista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarListaCampus(@RequestBody @Valid List<CampusDto> dto) {
		
		try {
			List<Campus> lista = dto.stream().map(x -> x.convertToEntity()).collect(Collectors.toList());
			lista = service.criarCampusLista(lista);
			return new ResponseEntity(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
