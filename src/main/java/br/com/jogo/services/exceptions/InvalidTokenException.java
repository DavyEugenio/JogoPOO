package br.com.jogo.services.exceptions;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException(String msg) {
		super(msg);
	}

	public InvalidTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
