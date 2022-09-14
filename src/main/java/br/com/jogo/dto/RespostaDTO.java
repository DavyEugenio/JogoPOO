package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RespostaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer registroPartida;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer alternativa;

	public RespostaDTO() {
	}

	public Integer getRegistroPartida() {
		return registroPartida;
	}

	public Integer getAlternativa() {
		return alternativa;
	}
}
