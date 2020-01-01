package com.ramon.sisu.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaga")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "tipo_vaga_id")
	private TipoVaga tipoVaga;
	
	@ManyToOne
	@JoinColumn(name = "curso_faculdade_id")
	private CursoFaculdade cursoFaculdade;
	
}
