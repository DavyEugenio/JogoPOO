package br.com.jogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.jogo.domain.Jogador;

public class JogadorTests {

	@SuppressWarnings("unused")
	@Test
	public void testJogador() {
		Jogador jog1 = new Jogador(0, "jose", null, null, null, 0, 0, 0, 0, LocalDate.now().minusDays(1), 0, null);
	}

	@SuppressWarnings("unused")
	@Test
	final void testJogadorString() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
	}

	@Test
	final void testAddPontuacao() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		jog.addPontuacao(2);
		assertEquals(2, jog.getPontuacaoTotal());
		// System.out.println("Pontuacao: " + jog.getPontuacaoTotal());
	}

	@Test
	final void testRegisterAccess() {
		LocalDate today1 = LocalDate.now();
		LocalDate yesterday1 = LocalDate.now().minusDays(1);
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		if (today1 != yesterday1) {
			jog.registerAccess();
			jog.addPontuacao(1);
		}
	}

	@Test
	final void testAddQtdPartidas() {
		Jogador jog1 = new Jogador(0, "jose", null, null, null, 0, 0, 0, 0, LocalDate.now().minusDays(1), 0, null);
		jog1.addQtdPartidas();
	}

	@Test
	final void testAddSaldo() {
		Jogador jog1 = new Jogador(0, "jose", null, null, null, 0, 0, 0, 0, LocalDate.now().minusDays(1), 0, null);
		jog1.addSaldo(100);
	}

	@Test
	final int testGetNumeroPartidas() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		jog.addQtdPartidas();
		return jog.getNumeroPartidas();
	}

	@Test
	final LocalDate testGetUltimoAcesso() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		return jog.getUltimoAcesso();
	}

}
