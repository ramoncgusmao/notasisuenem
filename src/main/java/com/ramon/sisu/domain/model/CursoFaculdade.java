package com.ramon.sisu.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursoFaculdade")
@Data
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
	
	@Column(name = "turno")
	@Enumerated(EnumType.ORDINAL)
	private Turno turno;
	
	
	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private Periodo periodo;
	
	
	@ManyToOne
	@JoinColumn(name = "campus_id")
	private Campus campus;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@OneToMany(mappedBy = "cursoFaculdade", fetch = FetchType.EAGER, targetEntity = Vaga.class,
            cascade=CascadeType.ALL, orphanRemoval = true)
	@Fetch(value=FetchMode.JOIN)
	private List<Vaga> vagas = new ArrayList<>();
	
	
	public double sumPesos() {
		return (redacaoPeso + naturezaPeso + matematicaPeso + humanaPeso + linguagemPeso);
	}
	
}
