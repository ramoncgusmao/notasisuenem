package com.ramon.sisu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByNome(String nome);

	Usuario findByEmail(String email);

}
