package br.com.jogo.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jogo.domain.Questao;
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
		obj.setAlternativas(
						objDto
						.getAlternativas()
						.stream()
						.map((a) -> alternativaService.fromDTO(a))
						.collect(Collectors.toSet())
						);
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
}
