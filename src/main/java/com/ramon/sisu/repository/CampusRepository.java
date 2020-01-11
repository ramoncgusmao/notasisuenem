package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.sisu.domain.model.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer>{

	Optional<Campus> findByNome(String nome);

}
