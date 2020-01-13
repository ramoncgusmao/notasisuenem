package com.ramon.sisu.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.sisu.domain.dto.DadosNotaDeCorteDto;
import com.ramon.sisu.domain.dto.NotaDeCorteDto;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.service.NotaDeCorteService;


@RestController
@RequestMapping("/notaDeCorte")
public class NotaDeCorteResource {

	@Autowired
	private NotaDeCorteService service;
	
	@PostMapping()
	public ResponseEntity criarNotaDeCorte(@RequestBody @Valid DadosNotaDeCorteDto dto) {
		
		try {
			List<NotaDeCorte> notaDeCortes = service.criarNotaDeCorte(dto);
			return new ResponseEntity(notaDeCortes, HttpStatus.CREATED);
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
	public ResponseEntity criarListaNotaDeCorte(@RequestBody @Valid List<NotaDeCorteDto> lista) {
		
		try {
			List<NotaDeCorte> notaDeCorteList = lista.stream().map(dto -> dto.convertToEntity()).collect(Collectors.toList());
			notaDeCorteList = service.criarNotaDeCorteLista(notaDeCorteList);
			return new ResponseEntity(notaDeCorteList, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
