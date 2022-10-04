package br.com.jogo.factories;

import br.com.jogo.domain.RegistroPartida;

public class RegistroPartidaFactory {
	

	public static RegistroPartida generate() {
		return new RegistroPartida(ConfiguracaoPartidaFactory.generate(),JogadorFactory.generate());

	}

}
