package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.RegistroPartida;

public class RegistroPartidaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer configuracaoPartida;

	public RegistroPartidaNewDTO() {
	}

	public Integer getConfiguracaoPartida() {
		return configuracaoPartida;
	}
	
	public RegistroPartida toEntity() {
		if (configuracaoPartida != null) {
			return new RegistroPartida(new ConfiguracaoPartida(configuracaoPartida, null, null), null);
		}
		return new RegistroPartida();
	}
}
