package br.com.jogo.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Alternativa;

public class AlternativaFactory {

	public static Alternativa generate(boolean isCorrect) {

		Faker faker = new Faker(new Locale("pt-BR"));
		return new Alternativa(faker.lorem().sentence(), isCorrect);

	}
}
