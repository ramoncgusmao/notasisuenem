package com.ramon.sisu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.sisu.domain.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

	public boolean existsByNome(String nome);

}
