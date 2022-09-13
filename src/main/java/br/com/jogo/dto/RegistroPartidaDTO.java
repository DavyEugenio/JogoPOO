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

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getMomento() {
		return momento;
	}

	public void setMomento(LocalDateTime momento) {
		this.momento = momento;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	
}
