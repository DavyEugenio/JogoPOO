package br.com.jogo.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Jogador;

public class JogadorFactory {
	public static Jogador generate() {

		Faker faker = new Faker(new Locale("pt-BR"));
		return new Jogador(faker.name().fullName(), faker.name().username(), faker.internet().emailAddress(),
				faker.internet().password());

	}

}
