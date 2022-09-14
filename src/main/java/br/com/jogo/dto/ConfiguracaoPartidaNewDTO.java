package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.Questao;

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

	public Set<Integer> getQuestoes() {
		return questoes;
	}

	public Set<Integer> getCategorias() {
		return categorias;
	}
	
	public ConfiguracaoPartida toEntity() {
		if(this.questoes != null) {
			return new ConfiguracaoPartida(null, this.questoes.stream().map(q -> new Questao(q, null, 0, null, null)).collect(Collectors.toSet()));
		}
		Set<Categoria> categorias = null;
		if(this.categorias != null) {
			categorias = this.categorias.stream().map(c -> new Categoria(c, null)).collect(Collectors.toSet());
		}
		return new ConfiguracaoPartida(null, categorias, nivel);
	}
}
