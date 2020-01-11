package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.repository.CampusRepository;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class CampusService {

	@Autowired
	private CampusRepository repository;
	
	@Autowired
	private FaculdadeService faculdadeService;
	public Campus criarCampus(Campus campus) {
		
		carregarFaculdade(campus);
		return repository.save(campus);
	}
	private void carregarFaculdade(Campus campus) {
		campus.setFaculdade(faculdadeService.findBySigla(campus.getFaculdade().getSigla()));
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
		Optional<Campus> campusOpt = repository.findByNome(nome);
		
		if(campusOpt.isPresent()) {
			return campusOpt.get();
		}
		
		throw new ObjectNotFoundException("nao foi encontrado campus " + nome);
	}

}
