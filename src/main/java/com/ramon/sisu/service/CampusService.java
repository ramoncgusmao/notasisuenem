package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.repository.CampusRepository;
import com.ramon.sisu.service.exception.DataIntegrityException;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class CampusService {

	@Autowired
	private CampusRepository repository;

	@Autowired
	private FaculdadeService faculdadeService;

	public Campus criarCampus(Campus campus) {

		try {
			campus.setMunicipio(campus.getMunicipio().toUpperCase());

			campus.setNome(campus.getNome().toUpperCase());
			carregarFaculdade(campus);
			campus.setNome(campus.getNome().toUpperCase());
			return repository.save(campus);
		} catch (DataException e) {
			throw new DataIntegrityException("erro ao salvar: " + e.getMessage());
		}

	}

	private void carregarFaculdade(Campus campus) {
		campus.setFaculdade(faculdadeService.findBySigla(campus.getFaculdade().getSigla().toUpperCase()));
	}

	public List<Campus> buscarCampuss() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public List<Campus> criarCampusLista(List<Campus> lista) {

		lista.stream().forEach(campus -> carregarFaculdade(campus));
		return repository.saveAll(lista);
	}

	public Campus findByNome(String nome) {
		Optional<Campus> campusOpt = repository.findByNome(nome.toUpperCase());

		if (campusOpt.isPresent()) {
			return campusOpt.get();
		}

		throw new ObjectNotFoundException("nao foi encontrado campus " + nome);
	}

	public Campus findByNomeAndMunicipio(Campus campusExample) {
		campusExample.setFaculdade(faculdadeService.findBySigla(campusExample.getFaculdade().getSigla()));

		Example<Campus> example = Example.of(campusExample, ExampleMatcher.matching().withIgnoreCase()
				.withIgnorePaths("id").withIgnoreNullValues());
		System.out.println(campusExample);
		Optional<Campus> campusOpt = repository.findOne(example);

		if (campusOpt.isPresent()) {
			return campusOpt.get();
		}
		return criarCampus(campusExample);
	}

	public Campus findByOne(Campus campusExample) {
		campusExample.setFaculdade(faculdadeService.findBySigla(campusExample.getFaculdade().getSigla()));

		Example<Campus> example = Example.of(campusExample, ExampleMatcher.matching().withIgnoreCase()
				.withIgnorePaths("id").withIgnoreNullValues());
		System.out.println(campusExample);
		Optional<Campus> campusOpt = repository.findOne(example);

		if (campusOpt.isPresent()) {
			return campusOpt.get();
		}

		throw new ObjectNotFoundException("não encontrado o campus " + campusExample.getNome());
	}
}
