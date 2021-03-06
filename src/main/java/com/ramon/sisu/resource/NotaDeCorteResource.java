package com.ramon.sisu.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.service.NotaDeCorteService;


@RestController
@RequestMapping("/notadecorte")
public class NotaDeCorteResource {

	@Autowired
	private NotaDeCorteService service;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarNotaDeCorte(@RequestBody @Valid DadosNotaDeCorteDto dto) {
		
		try {
			NotaDeCorte notaDeCorte = service.criarNotaDeCorte(dto);
			return new ResponseEntity(notaDeCorte, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity buscarNotaDeCortes() {
		
		try {
			List<NotaDeCorte> notaDeCortes = service.buscarNotaDeCortes();
			return ResponseEntity.ok(notaDeCortes);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/criarlista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity criarListaNotaDeCorte(@RequestBody @Valid List<DadosNotaDeCorteDto> lista) {
		
		try {
			List<NotaDeCorte> notaDeCorteList = service.criarNotaDeCorteLista(lista);
			return new ResponseEntity(notaDeCorteList, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity delete() {
		
		try {
			service.deleteAll();
			return ResponseEntity.ok("deletado");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
