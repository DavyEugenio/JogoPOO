package br.com.jogo.services.exceptions;

public class InvalidRoleUser extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidRoleUser(String msg) {
		super(msg);
	}

	public InvalidRoleUser(String msg, Throwable cause) {
		super(msg, cause);
	}
}