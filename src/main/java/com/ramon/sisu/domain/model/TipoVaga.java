package com.ramon.sisu.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_vaga")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoVaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, nullable = true)
	private String resumo;
	
	@Column(length = 10, nullable = true)
	private String sigla;
	
	@Column(length = 800, nullable = false)
	private String descricao;
	
}
