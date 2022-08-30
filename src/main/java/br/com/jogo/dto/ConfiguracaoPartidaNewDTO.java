package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;

public class ConfiguracaoPartidaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int nivel;
	private Set<Integer> questoes;
	private Set<Integer> categorias;

	public ConfiguracaoPartidaNewDTO() {
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Set<Integer> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<Integer> questoes) {
		this.questoes = questoes;
	}

	public Set<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Integer> categorias) {
		this.categorias = categorias;
	}

}
