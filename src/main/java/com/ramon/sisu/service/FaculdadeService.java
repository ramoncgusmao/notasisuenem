package com.ramon.sisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Faculdade;
import com.ramon.sisu.repository.FaculdadeRepository;

@Service
public class FaculdadeService {

	@Autowired
	private FaculdadeRepository repository;
	
	public Faculdade save(Faculdade faculdade) {
		
		return repository.save(faculdade);
	}

	public List<Faculdade> find(Integer id) {
		
		
		if(id == null) {
			return repository.findAll();
			
		}else {
			return repository.findByEstado_id(id);
		}
	}

	public List<Faculdade> saveList(List<Faculdade> faculdades) {
		// TODO Auto-generated method stub
		return repository.saveAll(faculdades);
	}

}
