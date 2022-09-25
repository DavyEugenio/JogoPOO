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
			ConfiguracaoPartida cp = new ConfiguracaoPartida();
			cp.setId(configuracaoPartidaId);
			return new RegistroPartida(cp, null);
		}
		return new RegistroPartida();
	}
}
