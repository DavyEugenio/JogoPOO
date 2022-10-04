package br.com.jogo;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.factories.QuestaoFactory;
import br.com.jogo.repositories.QuestaoRepository;
import br.com.jogo.services.QuestaoService;

@SpringBootTest
public class QuestoesTests {

	@Autowired
	QuestaoService qService;

	@Autowired
	QuestaoRepository qrepository;
	
	@Test
	void createQuestionTest() {
		Questao q = QuestaoFactory.generate();
		qService.insert(q);
	}
	
	@Test
	void getCorrectAlternativeTest() {
		Questao q = new Questao("Heroi da saga 'Legend of Zelda': ", 1, new Categoria("Games"),
				Set.of(new Alternativa("Mario", false), new Alternativa("Apolo", false), new Alternativa("Link", true),
						new Alternativa("Sheik", false)));

		q.getCorrectAlternative(); //printou a devida alternativa correta
		qService.insert(q);
	}
	
	@Test
	void questaoServicesTest() {
		Questao q = new Questao("O sistema operacional android eh baseado em qual sistema operacional? ", 1,
				new Categoria("T.I."), Set.of(new Alternativa("MS DOS", false), new Alternativa("Unix", false),
											  new Alternativa("Linux", true), new Alternativa("Windows", false)));

		qService.insert(q);
		qService.find(q.getId());
		qService.delete(q.getId());
	}

}
