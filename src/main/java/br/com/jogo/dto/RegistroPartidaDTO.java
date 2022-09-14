package br.com.jogo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.jogo.domain.RegistroPartida;

public class RegistroPartidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDateTime momento;
	private boolean ativa;
	private int pontuacao;

	public RegistroPartidaDTO(RegistroPartida obj) {
		this.id = obj.getId();
		this.momento = obj.getMomento();
		this.ativa = obj.isAtiva();
		this.pontuacao = obj.getPontuacao();
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getMomento() {
		return momento;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public int getPontuacao() {
		return pontuacao;
	}
}
