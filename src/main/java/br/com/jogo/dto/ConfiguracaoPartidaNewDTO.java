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
	private Set<Integer> questoesIds;
	private Set<Integer> categoriasIds;

	public ConfiguracaoPartidaNewDTO() {
	}

	public int getNivel() {
		return nivel;
	}

	public Set<Integer> getQuestoesIds() {
		return questoesIds;
	}

	public Set<Integer> getCategoriasIds() {
		return categoriasIds;
	}
	
	public ConfiguracaoPartida toEntity() {
		if(this.questoesIds != null || !this.questoesIds.isEmpty()) {
			return new ConfiguracaoPartida(null, this.questoesIds.stream().map(q -> new Questao(q, null, 0, null, null)).collect(Collectors.toSet()));
		}
		Set<Categoria> categorias = null;
		if(this.categoriasIds != null || !this.categoriasIds.isEmpty()) {
			categorias = this.categoriasIds.stream().map(c -> new Categoria(c, null)).collect(Collectors.toSet());
		}
		return new ConfiguracaoPartida(null, categorias, nivel);
	}
}
