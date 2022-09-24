package br.com.jogo.dto;

import javax.validation.constraints.NotEmpty;

import br.com.jogo.domain.Alternativa;

public class AlternativaNewDTO extends AlternativaBasicData {
	private boolean correta;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	protected AlternativaNewDTO(String texto, boolean correta) {
		super(texto);
		this.correta = correta;
	}

	public boolean isCorreta() {
		return correta;
	}

	public Alternativa toEntity() {
		return new Alternativa(getTexto(), correta);
	}
}
