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
@Table(name = "cursoFaculdade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoFaculdade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "redacao_peso")
	private double redacaoPeso;
	
	@Column(name = "natureza_peso")
	private double naturezaPeso;
	
	@Column(name = "humana_peso")
	private double humanaPeso;
	
	@Column(name = "linguagem_peso")
	private double linguagemPeso;
	
	@Column(name = "matematica_peso")
	private double matematicaPeso;
	
	@Column(name = "media_minima")
	private double mediaMinima;
	
	@Column(name = "possui_cota_regional")
	private boolean possuiCotaRegional;
	
	@Column(name = "porcentagem_regional")
	private double porcentagemRegional;
	
	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private Periodo periodo;
	
	
	@ManyToOne
	@JoinColumn(name = "campus_id")
	private Campus campus;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	
	
	
	
}
