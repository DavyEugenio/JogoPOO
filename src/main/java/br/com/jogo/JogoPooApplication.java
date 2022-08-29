package br.com.jogo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.services.QuestaoService;

@SpringBootApplication
public class JogoPooApplication {


	private static QuestaoService qrepo = new QuestaoService();
	
	
	public static void main(String[] args) {
		SpringApplication.run(JogoPooApplication.class, args);
		ArrayList<Alternativa> alts = new ArrayList<>();
		alts.addAll(List.of(new Alternativa("Wagner", false), new Alternativa("Beethoven", true), new Alternativa("Beethoven", false)));
		Questao q = new Questao("Autor de Moonlight Sonata", 2, new Categoria("Música"), alts);
		
		qrepo.insert(q);
		
	}

}
