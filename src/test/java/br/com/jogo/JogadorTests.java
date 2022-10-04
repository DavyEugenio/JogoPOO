package br.com.jogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Jogador;
import br.com.jogo.factories.JogadorFactory;
import br.com.jogo.services.JogadorService;

@SpringBootTest
public class JogadorTests {

	@Autowired
	JogadorService jogadorService;

	@Test
	final void testAddPontuacao() {
		Jogador jog = JogadorFactory.generate();
		jog.addPontuacao(2);
		assertEquals(2, jog.getPontuacaoTotal());
	}

	@Test
	final void testAddQtdPartidas() {
		Jogador jog = JogadorFactory.generate();
		jog.addQtdPartidas();
		assertEquals(1, jog.getQtdPartidas());
	}

	@Test
	final void testAddSaldo() {
		Jogador jog = JogadorFactory.generate();
		jog.addSaldo(100);
		assertEquals(100, jog.getSaldo());
	}

	@Test
	void testRegisterAcess() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "maeeer@gmail.com", "12345678");
		jog.registerAccess();
		assertEquals(jog.getUltimoAcesso(), LocalDate.now());
	}
	
	@Test
	void testInsertJogador() {
		Jogador jog = JogadorFactory.generate();
		jog = jogadorService.insert(jog);
		assertNotEquals(jog.getId(), null);
	}

}
