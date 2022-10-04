package br.com.jogo.factories;

import java.util.Locale;
import java.util.Random;
import java.util.Set;

import com.github.javafaker.Faker;

import br.com.jogo.domain.Questao;

public class QuestaoFactory {
	public static Questao generate() {

		Random random = new Random();
		Faker faker = new Faker(new Locale("pt-BR"));

		return new Questao(faker.lorem().sentence(), random.nextInt(5) + 1, CategoriaFactory.generate(),
				Set.of(AlternativaFactory.generate(true), AlternativaFactory.generate(false),
						AlternativaFactory.generate(false), AlternativaFactory.generate(false)));
	}

}
