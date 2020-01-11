package com.ramon.sisu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.sisu.domain.model.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer>{

}
