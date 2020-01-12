package com.ramon.sisu.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Min(1)
	@Max(5)
	@Column(nullable = false)
	private Integer dia;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private Periodo periodo;
	
	
}
