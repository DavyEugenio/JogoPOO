package br.com.jogo.factories;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Categoria;

public class CategoriaFactory {
	public static Categoria generate() {
		
		Faker faker = new Faker(new Locale("pt-BR"));
		return new Categoria(faker.funnyName().name());
	}

}
