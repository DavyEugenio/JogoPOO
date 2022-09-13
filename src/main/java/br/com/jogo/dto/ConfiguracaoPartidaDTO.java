package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;

public class ConfiguracaoPartidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int nivel;
	private Set<QuestaoDTO> questoes;
	private Set<CategoriaDTO> categorias;

	public ConfiguracaoPartidaDTO(Integer id, int nivel, Set<QuestaoDTO> questoes, Set<CategoriaDTO> categorias) {
		this.id = id;
		this.nivel = nivel;
		this.questoes = questoes;
		this.categorias = categorias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Set<QuestaoDTO> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<QuestaoDTO> questoes) {
		this.questoes = questoes;
	}

	public Set<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

}
