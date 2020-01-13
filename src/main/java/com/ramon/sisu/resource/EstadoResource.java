package com.ramon.sisu.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.model.Estado;
import com.ramon.sisu.service.EstadoService;

@RestController
@RequestMapping("/estado")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@GetMapping("/criarInicial")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarInicial() {
		
		String resultado = service.criarInicial();
			
		return ResponseEntity.ok(resultado);
	}
	
	
	@GetMapping("/")
	public ResponseEntity buscarEstado() {
		
		try {
			List<Estado> estados = service.buscarEstados();
			return ResponseEntity.ok(estados);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
