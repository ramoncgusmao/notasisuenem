package com.ramon.sisu.service;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.model.Perfil;
import com.ramon.sisu.domain.model.Usuario;
import com.ramon.sisu.repository.UsuarioRepository;
import com.ramon.sisu.security.UserSS;
import com.ramon.sisu.service.exception.DataIntegrityException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository repository;

	@Transient
	public Usuario save(Usuario usuario) {

		usuario.setSenha(pe.encode(usuario.getSenha()));

		return repository.save(usuario);
	}

	public Usuario findById(int id) {

		Optional<Usuario> usuarioOpt = repository.findById(id);
		if (usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		} else {
			throw new DataIntegrityException("não existe usuario com esse id");
		}

	}

	public Usuario autenticar(String email) throws DataIntegrityException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario != null) {
			return usuario;
		} else {
			throw new DataIntegrityException("não existe usuario com esse cpf ou senha");
		}

	}

	public Usuario editar(Integer id, Usuario usuario) throws DataIntegrityException {
		Usuario user = findById(id);
		usuario.setId(user.getId());
		Objects.requireNonNull(usuario.getId());
		return save(usuario);
	}

	public String primeiroLogin() {

		Usuario usuario = new Usuario();
		usuario.setNome("Admin master");
		usuario.setSenha("123admin123");
		usuario.setEmail("admin@admin.com.br");
		usuario.addPerfil(Perfil.ADMIN);
		System.out.println("veio aqui e parou");

		int quantidade = repository.findAll().size();
		if (quantidade > 0) {
			return ("ja existe um usuario admin criado");
		} else {
			save(usuario);
			return ("Usuario master cadastrado - email : admin@admin.com.br   senha: 123admin123");
		}

	}

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(Integer id) {

		repository.deleteById(id);

	}

}
