package br.com.jogo.services.exceptions;

public class InvalidNextQuestionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidNextQuestionException(String msg) {
		super(msg);
	}

	public InvalidNextQuestionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
