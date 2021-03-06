package com.ramon.sisu.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.sisu.domain.dto.NotaIndividualRespostaDto;
import com.ramon.sisu.domain.model.Campus;
import com.ramon.sisu.domain.model.CursoFaculdade;
import com.ramon.sisu.domain.model.Dia;
import com.ramon.sisu.domain.model.Media;
import com.ramon.sisu.domain.model.NotaDeCorte;
import com.ramon.sisu.domain.model.NotaIndividual;
import com.ramon.sisu.domain.model.Periodo;
import com.ramon.sisu.domain.model.TipoVaga;
import com.ramon.sisu.domain.model.Vaga;
import com.ramon.sisu.service.exception.ObjectNotFoundException;

@Service
public class NotaIndividualService {

	@Autowired
	public CursoService cursoService;

	@Autowired
	public FaculdadeService faculdadeService;

	@Autowired
	public CursoFaculdadeService cursoFaculdadeService;

	@Autowired
	public TipoVagaService tipoVagaService;

	@Autowired
	public DiaService diaService;

	public List<NotaIndividualRespostaDto> buscarNotas(NotaIndividual notaIndividual) {
		List<CursoFaculdade> cursosFaculdade = buscarListaDeFaculdades(notaIndividual);

		notaIndividual.setMediaNormal(calcularMediaNormal(notaIndividual));
		List<NotaIndividualRespostaDto> resposta = cursosFaculdade.stream()
				.map(x -> converterNotaIndividualResposta(x, notaIndividual)).collect(Collectors.toList());

		return resposta.stream().sorted(Comparator.comparing(NotaIndividualRespostaDto::getDiferencaNotaNormal).reversed())
				.collect(Collectors.toList());
	}

	private NotaIndividualRespostaDto converterNotaIndividualResposta(CursoFaculdade cursoFaculdade, NotaIndividual notaIndividual) {

		NotaIndividualRespostaDto notaIndividualRespostaDto = new NotaIndividualRespostaDto();

		carregarValoresCursoFaculdade(cursoFaculdade, notaIndividualRespostaDto);
		carregarValoresMedia(cursoFaculdade, notaIndividualRespostaDto, notaIndividual);
		
		Vaga vaga = vagaAmplaConcorrencia(cursoFaculdade);
		if(vaga != null) {
			notaIndividualRespostaDto.setVagaAmplaConcorrencia(vaga.getQuantidade());
			carregarNotaDeCorteAtual(vaga,notaIndividualRespostaDto,cursoFaculdade.getPeriodo());
			
		}
		

		
		
		notaIndividualRespostaDto.setVagas(cursoFaculdade.sumQuantidadeVagas());
		
		return notaIndividualRespostaDto;
	}

	private void carregarNotaDeCorteAtual(Vaga vaga, NotaIndividualRespostaDto notaIndividualRespostaDto,
			Periodo periodo) {
		Optional<Dia> diaOpt = diaService.findByPeriodo(periodo);
		if (!diaOpt.isPresent()) {
			return;
		}

		Dia dia = diaOpt.get();
		Optional<NotaDeCorte> notaDeCorteOpt = vaga.getNotadecorte().stream()
				.filter(nota -> nota.getDia().comparar(dia)).findFirst();
		if (!notaDeCorteOpt.isPresent()) {
			return;
		}
		
		
		NotaDeCorte notaDeCorte = notaDeCorteOpt.get();
		try {
			
	
		notaIndividualRespostaDto.setNotaDeCorte(notaDeCorte.getNota());
		notaIndividualRespostaDto.setDiferencaNotaNormal(notaIndividualRespostaDto.getNotaOrder() - notaDeCorte.getNota());
		if(!notaIndividualRespostaDto.getNotaCotaRegional().contains("-")){
			
			notaIndividualRespostaDto.setDiferencaNotaRegional(Double.parseDouble(notaIndividualRespostaDto.getNotaCotaRegional().replace(',', '.')) - notaDeCorte.getNota());
		}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	private void carregarValoresCursoFaculdade(CursoFaculdade cursoFaculdade,
			NotaIndividualRespostaDto notaIndividualRespostaDto) {
		notaIndividualRespostaDto.setId(cursoFaculdade.getId());

		notaIndividualRespostaDto.setEstado(cursoFaculdade.getCampus().getFaculdade().getEstado().getSigla());
		notaIndividualRespostaDto.setFaculdade(cursoFaculdade.getCampus().getFaculdade().getSigla());
		notaIndividualRespostaDto.setCampus(cursoFaculdade.getCampus().getNome());
		notaIndividualRespostaDto.setCidade(cursoFaculdade.getCampus().getMunicipio());
		notaIndividualRespostaDto.setCurso(cursoFaculdade.getCurso().getNome());
	}

	private void carregarValoresMedia(CursoFaculdade cursoFaculdade,
			NotaIndividualRespostaDto notaIndividualRespostaDto, NotaIndividual notaIndividual) {

		double media = calcularMedia(cursoFaculdade, notaIndividual);
		notaIndividualRespostaDto.setNotaOrder(media);
		notaIndividualRespostaDto.setNota(converterEmString(media));
		if (cursoFaculdade.isPossuiCotaRegional()) {
			double porcentagem = (cursoFaculdade.getPorcentagemRegional() + 100) / 100;
			notaIndividualRespostaDto.setNotaCotaRegional(converterEmString(media * porcentagem));
		} else {
			notaIndividualRespostaDto.setNotaCotaRegional("-");
		}
	}

	private String converterEmString(double valor) {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}

	private Vaga vagaAmplaConcorrencia(CursoFaculdade cursoFaculdade) {
		Optional<Vaga> vagaopt = cursoFaculdade.getVagas().stream()
				.filter(x -> x.getTipoVaga().getDescricao().equals("Ampla concorrência")).findFirst();

		if (vagaopt.isPresent()) {
			return vagaopt.get();
		}
		return null;
	}

	private double calcularMediaNormal(NotaIndividual notaIndividual) {
		double media = notaIndividual.getNotaHumanas() + notaIndividual.getNotaRedacao()
				+ notaIndividual.getNotaNatureza() + notaIndividual.getNotaLinguagens()
				+ notaIndividual.getNotaMatematica();
		return media / 5;
	}

	private double calcularMedia(CursoFaculdade cursoFaculdade, NotaIndividual notaIndividual) {

		double media = cursoFaculdade.getHumanaPeso() * notaIndividual.getNotaHumanas()
				+ cursoFaculdade.getRedacaoPeso() * notaIndividual.getNotaRedacao()
				+ cursoFaculdade.getNaturezaPeso() * notaIndividual.getNotaNatureza()
				+ cursoFaculdade.getLinguagemPeso() * notaIndividual.getNotaLinguagens()
				+ cursoFaculdade.getMatematicaPeso() * notaIndividual.getNotaMatematica();

		media = media / (cursoFaculdade.sumPesos());
		return media;
	}

	public List<CursoFaculdade> buscarListaDeFaculdades(NotaIndividual notaIndividual) {

		try {
			CursoFaculdade cursoFaculdadeExample = new CursoFaculdade();

			carregarCurso(notaIndividual, cursoFaculdadeExample);

			return cursoFaculdadeService.findAll(cursoFaculdadeExample);
		} catch (Exception e) {
			throw new ObjectNotFoundException(e.getMessage());
		}

	}

	private void carregarCurso(NotaIndividual notaIndividual, CursoFaculdade cursoFaculdadeExample) {
		if (notaIndividual.getCurso() != null) {
			cursoFaculdadeExample.setCurso(cursoService.findById(notaIndividual.getCurso().getId()));

			System.out.println(cursoFaculdadeExample.getCurso());
		}
	}

}
