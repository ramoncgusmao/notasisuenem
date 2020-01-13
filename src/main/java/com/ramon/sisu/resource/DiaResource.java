package com.ramon.sisu.resource;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.DiaDto;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.service.DiaService;

@RestController
@RequestMapping("/dia")
public class DiaResource {

	@Autowired
	private DiaService service;
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criar(@RequestBody @Valid DiaDto dto) {
		
		try {
			Dia dia = dto.convertToEntity();
			dia = service.save(dia);
			return new ResponseEntity<Dia>(dia, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping("/{periodo}")
	public ResponseEntity buscar(@PathVariable("periodo") String periodo) {
		
		try {
			
			List<Dia> dias = service.find(Dia.builder()
							.periodo(Periodo.builder()
								.nome(periodo)
								.build())
							.build());
			
			return ResponseEntity.ok(dias);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
