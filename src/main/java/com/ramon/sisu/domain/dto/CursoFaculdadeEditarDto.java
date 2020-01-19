package com.ramon.sisu.domain.dto;

import com.ramon.sisu.domain.model.CursoFaculdade;

import lombok.Data;

@Data
public class CursoFaculdadeEditarDto {

	
    private double redacaoPeso;
    private double notaMinimaRedacao;
    private double naturezaPeso;
    private double notaMinimaNatureza;
    private double humanaPeso;
    private double notaMinimaHumana;
    private double linguagemPeso;
    private double notaMinimaLinguagem;
    private double matematicaPeso;
    private double notaMinimaMatematica;
    private double mediaMinima;
    private boolean possuiCotaRegional;
    private double porcentagemRegional;
    
    
    public CursoFaculdade convertToEntity() {
    	CursoFaculdade cursoFaculdade = new CursoFaculdade();
    	cursoFaculdade.setRedacaoPeso(redacaoPeso);
    	cursoFaculdade.setNotaMinimaRedacao(notaMinimaRedacao);
    	cursoFaculdade.setNaturezaPeso(naturezaPeso);
    	cursoFaculdade.setNotaMinimaNatureza(notaMinimaNatureza);
    	cursoFaculdade.setHumanaPeso(humanaPeso);
    	cursoFaculdade.setNotaMinimaHumana(notaMinimaHumana);
    	cursoFaculdade.setLinguagemPeso(linguagemPeso);
    	cursoFaculdade.setNotaMinimaLinguagem(notaMinimaLinguagem);
    	cursoFaculdade.setMatematicaPeso(matematicaPeso);
    	cursoFaculdade.setNotaMinimaMatematica(notaMinimaMatematica);
    	cursoFaculdade.setPossuiCotaRegional(possuiCotaRegional);
    	cursoFaculdade.setPorcentagemRegional(porcentagemRegional);
    	return cursoFaculdade;
    }
   
}
