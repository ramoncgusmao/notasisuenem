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
@Table(name = "nota_de_corte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaDeCorte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private double nota;

	@ManyToOne
	@JoinColumn(name = "dia_id")
	private Dia dia;
	
	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;
	
}
