package br.com.jogo.dto;

import java.io.Serializable;

public class RegistroPartidaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer configuracaoPartida;

	public RegistroPartidaNewDTO() {
	}

	public Integer getConfiguracaoPartida() {
		return configuracaoPartida;
	}

	public void setConfiguracaoPartida(Integer configuracaopartida) {
		this.configuracaoPartida = configuracaopartida;
	}

}
