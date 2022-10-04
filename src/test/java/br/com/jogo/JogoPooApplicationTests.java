package br.com.jogo;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.repositories.QuestaoRepository;
import br.com.jogo.services.QuestaoService;

@SpringBootTest
class JogoPooApplicationTests {

	@Autowired
	private QuestaoService qService;

	@Autowired
	QuestaoRepository qrepository;

	@Test
	void contextLoads() {
	}

	@Test
	void inserirQuestao() {
		Questao q = new Questao("Autor de Moonlight Sonata", 2, new Categoria("Música"),
				Set.of(new Alternativa("Wagner", false), new Alternativa("Beethoven", true),
						new Alternativa("Mozart", false), new Alternativa("Paganini", false)));
		qService.insert(q);
	}

	@Test
	void findOne() {
		System.out.println(qService
				.findOneNotIn(Set.of(new Questao(1, null, 0, null, null), new Questao(2, null, 0, null, null))));
	}

	@Test
	void findQuest() {
		System.out.println(qService.find(1));
		qService.find(2);
		qService.findAll();
		qService.findOneByNivel(2, Set.of(new Questao(2, null, 0, null, null)));
	}

}
