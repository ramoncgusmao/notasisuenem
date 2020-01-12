package com.ramon.sisu.domain.model;

public enum Turno {

	MATUTINO(1, "MATUTINO"),
	VESPERTINO(2, "VESPERTINO"),
	NOTURNO(3, "NOTURNO"),
	INTEGRAL(4, "INTEGRAL");
	
	
	private int cod;
	private String descricao;
	
	private Turno(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Turno toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Turno x : Turno.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id invalido: " + cod);
	}
}
