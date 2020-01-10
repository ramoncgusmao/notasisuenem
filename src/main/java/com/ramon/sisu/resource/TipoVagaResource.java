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

import com.ramon.sisu.domain.dto.TipoVagaDto;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.service.TipoVagaService;


@RestController
@RequestMapping("/tipovaga")
public class TipoVagaResource {

	@Autowired
	private TipoVagaService service;
	
	@PostMapping()
	public ResponseEntity criarTipoVaga(@RequestBody @Valid TipoVagaDto dto) {
		
		try {
			TipoVaga tipoVaga = dto.convertToEntity();
			tipoVaga = service.criarTipoVaga(tipoVaga);
			return new ResponseEntity(tipoVaga, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity buscarTipoVagas() {
		
		try {
			List<TipoVaga> tipoVagas = service.buscarTipoVagas();
			return new ResponseEntity(tipoVagas, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
