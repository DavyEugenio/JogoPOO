package br.com.jogo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.QuestaoService;

@SpringBootTest
class JogoPooApplicationTests {
	
	@Autowired
	private QuestaoService qrepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void inserirQuestao() {
		ArrayList<Alternativa> alts = new ArrayList<>();
		alts.addAll(List.of(new Alternativa("Wagner", false), new Alternativa("Beethoven", true), new Alternativa("Beethoven", false)));
		Questao q = new Questao("Autor de Moonlight Sonata", 2, new Categoria("Música"), alts);
		qrepo.insert(q);
	}
}
