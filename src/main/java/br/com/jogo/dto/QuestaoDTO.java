package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;

public class QuestaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String texto;
	private int nivel;
	private Integer categoriaId;
	private Set<AlternativaBasicData> alternativas;

	public QuestaoDTO() {
	}

	public QuestaoDTO(Questao obj) {
		this.id = obj.getId();
		this.texto = obj.getTexto();
		this.nivel = obj.getNivel();
		this.categoriaId = obj.getCategoria().getId();
		this.alternativas = obj.getAlternativas().stream().map(AlternativaDTO::new).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
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

	public Set<AlternativaBasicData> getAlternativas() {
		return alternativas;
	}

	public Questao toEntity() {
		return new Questao(id, texto, nivel, new Categoria(categoriaId, null),
				alternativas.stream().map(a -> ((AlternativaNewDTO) a).toEntity()).collect(Collectors.toSet()));
	}
}
