package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

	public boolean existsByNome(String nome);

	public Optional<Curso> findByNome(String nome);

}
