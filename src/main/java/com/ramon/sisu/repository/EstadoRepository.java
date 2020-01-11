package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	Optional<Estado> findBySigla(String sigla);

}
