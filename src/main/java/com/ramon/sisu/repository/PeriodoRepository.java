package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer>{

	Optional<Periodo> findByNome(String nome);

}
