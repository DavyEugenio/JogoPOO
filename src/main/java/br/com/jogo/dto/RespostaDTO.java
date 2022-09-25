package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.RegistroPartida;

public class RespostaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer registroPartidaId;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer alternativaId;

	public RespostaDTO() {
	}

	public Integer getRegistroPartidaId() {
		return registroPartidaId;
	}

	public Integer getAlternativaId() {
		return alternativaId;
	}

	public RegistroPartida getRegistroPartida() {
		return new RegistroPartida(registroPartidaId, null, false, 0, null, null);
	}

	public Alternativa getAlternativa() {
		return new Alternativa(alternativaId, null, false);
	}
}
