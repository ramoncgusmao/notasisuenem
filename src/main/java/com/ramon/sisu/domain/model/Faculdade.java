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
@Table(name = "faculdade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faculdade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 30)
	private String sigla;
	
	
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;
	
	
	
	
}
