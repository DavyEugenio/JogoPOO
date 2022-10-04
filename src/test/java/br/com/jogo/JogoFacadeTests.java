package br.com.jogo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jogo.domain.Admin;
import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.Questao;
import br.com.jogo.domain.RegistroPartida;
import br.com.jogo.facade.Jogo;
import br.com.jogo.factories.AdminFactory;
import br.com.jogo.factories.ConfiguracaoPartidaFactory;
import br.com.jogo.factories.RegistroPartidaFactory;
import br.com.jogo.repositories.AdminRepository;
import br.com.jogo.repositories.AlternativaRepository;
import br.com.jogo.repositories.ConfiguracaoPartidaRepository;
import br.com.jogo.repositories.JogadorRepository;
import br.com.jogo.repositories.QuestaoRepository;
import br.com.jogo.repositories.RegistroPartidaRepository;
import br.com.jogo.repositories.UsuarioRepository;
import br.com.jogo.services.AdminService;
import br.com.jogo.services.AlternativaService;
import br.com.jogo.services.ConfiguracaoPartidaService;
import br.com.jogo.services.JogadorService;
import br.com.jogo.services.QuestaoService;
import br.com.jogo.services.RegistroPartidaService;
import br.com.jogo.services.UsuarioService;

@SpringBootTest
class JogoFacadeTests {

	@Autowired
	AdminService adminService;
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	JogadorService JogadorService;
	@Autowired
	JogadorRepository JogadorRepository;

	@Autowired
	QuestaoService qService;
	@Autowired
	QuestaoRepository qrepository;

	@Autowired
	ConfiguracaoPartidaService CPService;
	@Autowired
	ConfiguracaoPartidaRepository CPRepository;

	@Autowired
	RegistroPartidaService RGService;
	@Autowired
	RegistroPartidaRepository RGRepository;

	@Autowired
	AlternativaService aService;
	@Autowired
	AlternativaRepository aRepository;

	@Autowired
	Jogo jogo;

	@Test
	final void testInsertAdmin() {
		Admin adm = AdminFactory.generate();
		adm = adminService.insert(adm);
		adminService.findAll();
	}

	@Test
	final void testInsertConfiguracaoPartida() {
		ConfiguracaoPartida cp = ConfiguracaoPartidaFactory.generate();
		CPService.insert(cp);
		// CPService.delete(cp.getId());

	}

	@Test
	void testfindJogadorByEmail() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		//JogadorService.insert(jog);
		jogo.findJogadorByEmail("mar@gmail.com");

	}

	@Test
	final void testAnswerQuestion() {

		Questao q = new Questao("Heroi da saga 'Legend of Zelda': ", 1, new Categoria("Game"),
				Set.of(new Alternativa("Mario", false), new Alternativa("Zelda", false), new Alternativa("Link", true),
						new Alternativa("Shrek", false)));
		RegistroPartida rg = RegistroPartidaFactory.generate();
		
		qService.insert(q);
		RGService.insert(rg);
		jogo.answerQuestion(rg, new Alternativa("Link", true));
	}

	@Test
	final void testFindPartidaAtivaByJogador() {
		Jogador jog = new Jogador("Marcos André", "marquinhos_katchau", "mar@gmail.com", "12345678");
		 jogo.findPartidaAtivaByJogador(jog);
		
		assertNotEquals(jogo.findPartidaAtivaByJogador(jog),RGService.findActiveByJogador(jog));
	}

}
