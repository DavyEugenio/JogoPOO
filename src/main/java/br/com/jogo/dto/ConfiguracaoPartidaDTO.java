package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jogo.domain.ConfiguracaoPartida;

public class ConfiguracaoPartidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int nivel;
	private Set<QuestaoDTO> questoes;
	private Set<CategoriaDTO> categorias;

	public ConfiguracaoPartidaDTO(ConfiguracaoPartida obj) {
		this.id = obj.getId();
		this.nivel = obj.getNivel();
		this.questoes = obj.getQuestoes().stream().map(QuestaoDTO::new).collect(Collectors.toSet());
		this.categorias = obj.getCategorias().stream().map(CategoriaDTO::new).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	public int getNivel() {
		return nivel;
	}

	public Set<QuestaoDTO> getQuestoes() {
		return questoes;
	}

	public Set<CategoriaDTO> getCategorias() {
		return categorias;
	}
}
