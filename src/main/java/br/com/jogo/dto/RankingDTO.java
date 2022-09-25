package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.Jogador;

public class RankingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer jogadorId;
	private String nomeUsuario;
	private int pontuacao;

	public RankingDTO(Jogador jogador, int pontuacao) {
		this.jogadorId = jogador.getId();
		this.nomeUsuario = jogador.getNomeUsuario();
		this.pontuacao = pontuacao;
	}

	public Integer getJogadorId() {
		return jogadorId;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public int getPontuacao() {
		return pontuacao;
	}

}
