package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.TipoVaga;

public interface TipoVagaRepository extends JpaRepository<TipoVaga, Integer>{

	Optional<TipoVaga> findBySigla(String sigla);

	Optional<TipoVaga> findByDescricao(String descricao);

}
