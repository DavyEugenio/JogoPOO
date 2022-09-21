package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.RegistroPartida;

public class RegistroPartidaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer configuracaoPartidaId;

	public RegistroPartidaNewDTO() {
	}

	public Integer getConfiguracaoPartidaId() {
		return configuracaoPartidaId;
	}
	
	public RegistroPartida toEntity() {
		if (configuracaoPartidaId != null) {
			return new RegistroPartida(new ConfiguracaoPartida(configuracaoPartidaId, null, null), null);
		}
		return new RegistroPartida();
	}
}
