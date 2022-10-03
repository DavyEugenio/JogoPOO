package br.com.jogo;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.QuestaoService;

@SpringBootTest
public class JogoPooValidationTests {

	@Autowired
	private QuestaoService qService;

	@Test
	void contextLoads() {
	}


	@Test
	void inserirQuestao1() {
		Questao q = new Questao("Qual destes não é um deus(a) grego(a):", 3, new Categoria("História!"),
				Set.of(new Alternativa("Hera", false), new Alternativa("Apolo", false),
						new Alternativa("Odisseu", true), new Alternativa("Hebe", false)));
		qService.insert(q);
	} 

	@Test
	void inserirQuestao2() {
		Questao q = new Questao("Qual destes não é um deus(a) grego(a): ", 4, new Categoria("Históriaa!"),
				Set.of(new Alternativa("Hera", false), new Alternativa("Apolo", false),
						new Alternativa("Odisseu", true), new Alternativa("", false)));
		qService.insert(q);
	} 
	
	@Test
	void integrationTest() {
		inserirQuestao1();
		
		inserirQuestao2();
	}

}
