package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Periodo;

public interface DiaRepository extends JpaRepository<Dia, Integer>{

	Optional<Dia> findByDiaAndPeriodo(Integer dia, Periodo periodo);

}
