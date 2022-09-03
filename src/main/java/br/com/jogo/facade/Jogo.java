package br.com.jogo.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.dto.AlternativaNewDTO;
import br.com.jogo.dto.CategoriaNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
import br.com.jogo.services.AlternativaService;
import br.com.jogo.services.CategoriaService;
import br.com.jogo.services.QuestaoService;

@Component
public class Jogo {
	@Autowired
	private QuestaoService questaoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private AlternativaService alternativaService;

	public Questao insertQuestao(QuestaoNewDTO objDto) {
		Questao obj = questaoService.fromDTO(objDto);
		obj.setCategoria(categoriaService.find(objDto.getCategoria()));
		obj.setAlternativas(objDto.getAlternativas().stream().map((a) -> alternativaService.fromDTO(a))
				.collect(Collectors.toSet()));
		return questaoService.insert(obj);
	}

	public Questao findQuestao(Integer id) {
		return questaoService.find(id);
	}

	public List<Questao> findAllQuestoes() {
		return questaoService.findAll();
	}

	public void deleteQuestao(Integer id) {
		questaoService.delete(id);
	}

	public Questao updateQuestao(QuestaoDTO objDto) {
		Questao obj = questaoService.fromDTO(objDto);
		obj.setCategoria(categoriaService.find(objDto.getCategoria().getId()));
		return questaoService.update(obj);
	}
	
	//--------------------------------Alternativa----------------------------------------------
	
	public Alternativa insertAlternativa(AlternativaNewDTO objDto) {
		Alternativa obj = alternativaService.fromDTO(objDto);
		return alternativaService.insert(obj);
	}
	
	public Alternativa findAlternativa(Integer id) {
		return alternativaService.find(id);
	}
	
	public List<Alternativa> findAllAlternativa() {
		return alternativaService.findAll();
	}
	
	public void deleteAlternativa(Integer id) {
		alternativaService.delete(id);
	}
	
	public Alternativa updateAlternativa(Alternativa obj) {
		return alternativaService.update(obj);
	}
	
	//--------------------------------Categoria----------------------------------------------
	
	public Categoria insertCategoria(CategoriaNewDTO objDto) {
		Categoria obj = categoriaService.fromDTO(objDto);
		return categoriaService.insert(obj);
	}
	
	public Categoria findCategoria(Integer id) {
		return categoriaService.find(id);
	}
	
	public List<Categoria> findAllCategorias() {
		return categoriaService.findAll();
	}

	public void deleteCategoria(Integer id) {
		categoriaService.delete(id);
	}/*

	public Categoria updateCategoria(CategoriaDTO objDto) {
		Categoria obj = categoriaService.fromDTO(objDto);
		obj.setCategoria(categoriaService.find(objDto.getCategoria().getId()));
		return categoriaService.update(obj);
	}*/
}	
