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

	public void setRegistroPartidaId(Integer registroPartidaId) {
		this.registroPartidaId = registroPartidaId;
	}

	public Integer getAlternativaId() {
		return alternativaId;
	}

	public void setAlternativaId(Integer alternativaId) {
		this.alternativaId = alternativaId;
	}

	public RegistroPartida getRegistroPartida() {
		return new RegistroPartida(registroPartidaId, null, false, 0, null, null);
	}

	public Alternativa getAlternativa() {
		return new Alternativa(alternativaId, null, false);
	}

	@Override
	public String toString() {
		return "RespostaDTO [registroPartidaId=" + registroPartidaId + ", alternativaId=" + alternativaId + "]";
	}
}
