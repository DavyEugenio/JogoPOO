package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.validation.QuestaoInsert;

@QuestaoInsert
public class QuestaoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String texto;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "O valor mínimo é 1", value = 1)
	@Max(message = "O valor máximo é 5", value = 5)
	private int nivel;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer categoriaId;
	@NotNull(message = "Preenchimento obrigatório!")
	private Set<AlternativaNewDTO> alternativas;

	public QuestaoNewDTO() {
	}

	public String getTexto() {
		return texto;
	}

	public int getNivel() {
		return nivel;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public Set<AlternativaNewDTO> getAlternativas() {
		return alternativas;
	}

	public Questao toEntity() {
		return new Questao(texto, nivel, new Categoria(categoriaId, null),
				alternativas.stream().map(a -> a.toEntity()).collect(Collectors.toSet()));
	}
}
