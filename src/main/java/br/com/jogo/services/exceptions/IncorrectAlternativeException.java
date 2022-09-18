package br.com.jogo.services.exceptions;

public class IncorrectAlternativeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectAlternativeException(String msg) {
		super(msg);
	}

	public IncorrectAlternativeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
