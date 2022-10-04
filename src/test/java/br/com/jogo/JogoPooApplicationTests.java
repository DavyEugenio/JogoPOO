package br.com.jogo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.Questao;
import br.com.jogo.repositories.QuestaoRepository;
import br.com.jogo.domain.RegistroPartida;
import br.com.jogo.facade.Jogo;
import br.com.jogo.services.JogadorService;
import br.com.jogo.services.QuestaoService;

@SpringBootTest
class JogoPooApplicationTests {

	@Autowired
	private QuestaoService qService;
	@Autowired
	private JogadorService jService;

	@Autowired
	private Jogo jogo;

	@Autowired
	QuestaoRepository qrepository;

	@Test
	void contextLoads() {
	}

	@Test
	void insertQuestao() {
		Questao q = new Questao("Pintor da Capela Sistina", 2, new Categoria("Arte"),
				Set.of(new Alternativa("Michelangelo", false), new Alternativa("Donatelo", true),
						new Alternativa("Leonardo", false), new Alternativa("Rafael", false)));
		qService.insert(q);
	}

	@Test
	void isPreseted() {
		ConfiguracaoPartida cp = new ConfiguracaoPartida(jService.find(1),
				qService.findAll().stream().collect(Collectors.toSet()));
		assertTrue(cp.isPredefinida());
	}

	@Test
	void insertRegistroPartida() {
		RegistroPartida rp = jogo.insertRegistroPartida(new RegistroPartida());
		assertNotNull(rp);
	}

	@Test
	void findOne() {
		System.out.println(qService.findOneNotIn(Set.of(new Questao(1, null, 0, null, null))).getTexto());
	}

	@Test
	void anwerQuestion() {
		RegistroPartida rp = jogo.insertRegistroPartida(new RegistroPartida());
		Questao q = rp.getUltimaQuestao();
		System.out.println(rp.getUltimaQuestao().getTexto());
		jogo.answerQuestion(rp, q.getCorrectAlternative());
		rp = jogo.findRegistroPartida(rp.getId());
		System.out.println(rp.getUltimaQuestao().getTexto());
	}

	@Test
	void findOne1() {
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
