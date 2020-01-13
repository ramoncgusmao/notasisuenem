package com.ramon.sisu.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ramon.sisu.domain.model.Perfil;
import com.ramon.sisu.domain.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDto {


	
	 @NotEmpty(message = "O nome é necessario")
	private String nome;
	 @NotEmpty(message = "A senha é necessaria")
	private String senha;
	 @NotNull(message = "O perfil é necessario")
	private Integer perfil;
	

	 
	 
	 @NotEmpty(message = "O email é necessario")
	private String email;
	 public Usuario convertToEntity() {
		 
		Usuario usuario =  new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.addPerfil(Perfil.toEnum(perfil));
		return usuario;
	}
}
