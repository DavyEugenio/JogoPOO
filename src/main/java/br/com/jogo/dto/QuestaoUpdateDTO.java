package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.validation.QuestaoUpdate;

@QuestaoUpdate
public class QuestaoUpdateDTO extends QuestaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Set<AlternativaNewDTO> alternativas;

	public QuestaoUpdateDTO() {
	}

	public Questao toEntity() {
		return new Questao(this.getCategoriaId(), this.getTexto(), this.getNivel(), new Categoria(this.getCategoriaId(), null),
				alternativas.stream().map(a -> a.toEntity()).collect(Collectors.toSet()));
	}
}
