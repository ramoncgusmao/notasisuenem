package com.ramon.sisu.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredencialDto implements Serializable{

	private String email;
	private String senha;
}
