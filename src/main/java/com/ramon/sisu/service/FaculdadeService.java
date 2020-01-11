package com.ramon.sisu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Faculdade;
import com.ramon.sisu.repository.FaculdadeRepository;
import com.ramon.sisu.service.exception.DataIntegrityException;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class FaculdadeService {

	@Autowired
	private FaculdadeRepository repository;
	
	@Autowired
	private EstadoService estadoService;
	
	public Faculdade save(Faculdade faculdade) {
		verificaExistencia(faculdade);
		carregarEstadoNaFaculdade(faculdade);
		return repository.save(faculdade);
	}

	public void verificaExistencia(Faculdade faculdade) {
		if(repository.existsBySigla(faculdade.getSigla())){
			throw new DataIntegrityException("a faculdade " + faculdade.getNome() + " já esta cadastrada ");
		}
	}
	private void carregarEstadoNaFaculdade(Faculdade faculdade) {
		faculdade.setEstado(estadoService.findBySigla(faculdade.getEstado().getSigla()));
	}

	private void validaCadastro(Faculdade faculdade) {
		verificaExistencia(faculdade);
		carregarEstadoNaFaculdade(faculdade);
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
		faculdades.stream().forEach( x -> validaCadastro(x));
		return repository.saveAll(faculdades);
	}

	
	public Faculdade findBySigla(String sigla) {
		Optional<Faculdade> faculdadeOpt = repository.findBySigla(sigla);
		
		if(faculdadeOpt.isPresent()) {
			return faculdadeOpt.get();
		}
		
		throw new ObjectNotFoundException("não existe faculdade com a sigla : " + sigla);
	}

}
