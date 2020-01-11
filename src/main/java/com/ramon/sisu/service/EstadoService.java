package com.ramon.sisu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Estado;
import com.ramon.sisu.repository.EstadoRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public String criarInicial() {
		
		long valor = repository.count();
		
		if(valor > 0 ) {
			return "ja havia criado todos os estados";
		}
		List<Estado> estados = carregarEstadoLista();

		repository.saveAll(estados);
		return "criado todos os estados";
	}

	private List<Estado> carregarEstadoLista() {
		List<Estado> estados = new ArrayList<Estado>();
		estados.add(Estado.builder().nome("Acre").sigla("AC").build());
		estados.add(Estado.builder().nome("Alagoas").sigla("AL").build());
		estados.add(Estado.builder().nome("Amapá").sigla("AP").build());
		estados.add(Estado.builder().nome("Amazonas").sigla("AM").build());
		estados.add(Estado.builder().nome("Bahia").sigla("BA").build());
		estados.add(Estado.builder().nome("Ceará").sigla("CE").build());
		estados.add(Estado.builder().nome("Distrito Federal").sigla("DF").build());
		estados.add(Estado.builder().nome("Espírito Santo").sigla("ES").build());
		estados.add(Estado.builder().nome("Goiás").sigla("GO").build());
		estados.add(Estado.builder().nome("Maranhão").sigla("MA").build());
		estados.add(Estado.builder().nome("Mato Grosso").sigla("MT").build());
		estados.add(Estado.builder().nome("Mato Grosso do Sul").sigla("MS").build());
		estados.add(Estado.builder().nome("Minas Gerais").sigla("MG").build());
		estados.add(Estado.builder().nome("Pará").sigla("PA").build());
		estados.add(Estado.builder().nome("Paraíba").sigla("PB").build());
		estados.add(Estado.builder().nome("Paraná").sigla("PR").build());
		estados.add(Estado.builder().nome("Pernambuco").sigla("PE").build());
		estados.add(Estado.builder().nome("Piauí").sigla("PI").build());
		estados.add(Estado.builder().nome("Rio de Janeiro").sigla("RJ").build());
		estados.add(Estado.builder().nome("Rio Grande do Norte").sigla("RN ").build());
		estados.add(Estado.builder().nome("Rio Grande do Sul").sigla("RS").build());
		estados.add(Estado.builder().nome("Rondônia").sigla("RO").build());
		estados.add(Estado.builder().nome("Roraima").sigla("RR").build());
		estados.add(Estado.builder().nome("Santa Catarina").sigla("SC").build());
		estados.add(Estado.builder().nome("São Paulo").sigla("SP").build());
		estados.add(Estado.builder().nome("Sergipe").sigla("SE").build());
		estados.add(Estado.builder().nome("Tocantins").sigla("TO").build());
		return estados;
	}

	public List<Estado> buscarEstados() {
		
		return repository.findAll();
	}

	public Estado findBySigla(String sigla) {
		Optional<Estado> estadoOpt = repository.findBySigla(sigla);
		
		if(estadoOpt.isPresent()) {
			return estadoOpt.get();
		}
		throw new ObjectNotFoundException("não foi possivel encontrar estado : " + sigla);
	}
	
}
