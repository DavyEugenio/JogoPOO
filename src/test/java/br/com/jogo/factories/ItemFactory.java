package br.com.jogo.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Item;

public class ItemFactory {
	public static Item generate() {

		Faker faker = new Faker(new Locale("pt-BR"));
		return new Item(faker.funnyName().name(), faker.funnyName().name(), faker.number().randomDigit(),
				faker.number().randomDigit(), faker.number().randomDigit());
	}

}
