package br.com.jogo;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.QuestaoService;

public class JogoPooValidationTests {
	
	@Autowired
	private QuestaoService qService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void inserirQuestao1() {
		Questao q = new Questao("Qual destes não é um deus(a) grego(a):", 1, new Categoria("História"),
				Set.of(new Alternativa("Hera", false), new Alternativa("Apolo", false),
						new Alternativa("Odisseu", true), new Alternativa("", false)));
		qService.insert(q);
	}//null camp
	
	@Test
	void inserirQuestao2() {
		Questao q = new Questao("Qual destes não é um deus(a) grego(a):", 1, new Categoria("História"),
				Set.of(new Alternativa("Hera", false), new Alternativa("Apolo", false),
						new Alternativa("Odisseu", true), new Alternativa("Hebe", false)));
		qService.insert(q);
	}//normal
	
	@Test
	void inserirQuestao3() {
		Questao q = new Questao("Qual destes não é um deus(a) grego(a):", 1, new Categoria("História"),
				Set.of(new Alternativa("Hera", false), new Alternativa("Apolo", false),
						new Alternativa("Odisseu", true), new Alternativa("Hebe", true)));
		qService.insert(q);
	} //2 true
}
