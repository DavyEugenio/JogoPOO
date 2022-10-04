package br.com.jogo.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Admin;

public class AdminFactory {
	public static Admin generate() {

		Faker faker = new Faker(new Locale("pt-BR"));
		return new Admin(faker.name().fullName(), faker.name().username(), faker.internet().emailAddress(),
				faker.internet().password());

	}

}
