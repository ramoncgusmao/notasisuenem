package com.ramon.sisu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Faculdade;

public interface FaculdadeRepository extends JpaRepository<Faculdade, Integer>{

	List<Faculdade> findByEstado_id(Integer id);

}
