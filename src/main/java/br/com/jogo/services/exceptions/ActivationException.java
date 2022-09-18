package br.com.jogo.services.exceptions;

public class ActivationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ActivationException(String msg) {
		super(msg);
	}

	public ActivationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
