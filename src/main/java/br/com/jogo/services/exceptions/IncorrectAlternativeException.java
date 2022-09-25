package br.com.jogo.services.exceptions;

import br.com.jogo.domain.Alternativa;

public class IncorrectAlternativeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Alternativa correctAlternative;
	public IncorrectAlternativeException(Alternativa correctAlternative) {
		super("Alternativa respondida está incorreta! Resposta correta: " + correctAlternative.getTexto());
		this.correctAlternative = correctAlternative;
	}

	public IncorrectAlternativeException(Alternativa correctAlternative, Throwable cause) {
		super("Alternativa respondida está incorreta! Resposta correta: " + correctAlternative.getTexto(), cause);
	}
	
	public Alternativa getCorrectAlternative() {
		return correctAlternative;
	}
}
