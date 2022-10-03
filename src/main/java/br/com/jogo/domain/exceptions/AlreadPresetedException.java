package br.com.jogo.domain.exceptions;

public class AlreadPresetedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadPresetedException() {
		super("Configuração de partida já está predefinida!");
	}

	public AlreadPresetedException(Throwable cause) {
		super("Configuração de partida já está predefinida!", cause);
	}

}
