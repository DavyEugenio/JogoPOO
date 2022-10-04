package br.com.jogo.factories;

import br.com.jogo.domain.ConfiguracaoPartida;

public class ConfiguracaoPartidaFactory {

	public static ConfiguracaoPartida generate() {
		return new ConfiguracaoPartida(JogadorFactory.generate());

	}

}
