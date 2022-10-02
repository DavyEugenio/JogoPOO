package br.com.jogo.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.RegistroPartida;
import br.com.jogo.dto.ConfiguracaoPartidaDTO;
import br.com.jogo.dto.ConfiguracaoPartidaNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.RankingDTO;
import br.com.jogo.dto.RegistroPartidaDTO;
import br.com.jogo.dto.RegistroPartidaNewDTO;
import br.com.jogo.dto.RespostaDTO;
import br.com.jogo.facade.Jogo;
import br.com.jogo.security.exceptions.AuthorizationException;
import br.com.jogo.security.exceptions.InvalidRoleUser;
import br.com.jogo.services.exceptions.ActivationException;
import br.com.jogo.services.exceptions.IncorrectAlternativeException;
import br.com.jogo.services.exceptions.InvalidNextQuestionException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(path = "/partidas")
public class PartidaResource {
	@Autowired
	private Jogo jogo;

	@RequestMapping(value = "/configuracoes/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConfiguracaoPartida> findConfiguracaoPartida(@PathVariable Integer id) {
		ConfiguracaoPartida obj = jogo.findConfiguracaoPartida(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/configuracoes", method = RequestMethod.POST)
	public ResponseEntity<Integer> insertConfiguracaoPartida(@Valid @RequestBody ConfiguracaoPartidaNewDTO objNewDto)
			throws AuthorizationException, InvalidRoleUser, ObjectNotFoundException {
		ConfiguracaoPartida obj = objNewDto.toEntity();
		System.out.print(obj);
		obj = jogo.insertConfiguracaoPartida(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}

	@RequestMapping(value = "/configuracoes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteConfiguracaoPartida(@PathVariable Integer id) {
		jogo.deleteConfiguracaoPartida(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/configuracoes", method = RequestMethod.GET)
	public ResponseEntity<List<ConfiguracaoPartidaDTO>> findAllConfiguracaoPartidas() {
		List<ConfiguracaoPartidaDTO> list = jogo.findAllConfiguracaoPartidas().stream().map(ConfiguracaoPartidaDTO::new)
				.toList();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@RequestBody RegistroPartidaNewDTO objNewDto)
			throws AuthorizationException, InvalidRoleUser {
		RegistroPartida obj = jogo.insertRegistroPartida(objNewDto.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RegistroPartida> findRegistroPartida(@PathVariable Integer id) {
		RegistroPartida obj = jogo.findRegistroPartida(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}/questao", method = RequestMethod.GET)
	public ResponseEntity<QuestaoDTO> sendLastQuestion(@PathVariable Integer id) throws ActivationException {
		QuestaoDTO obj = new QuestaoDTO(jogo.UltimaQuestaoRegistroPartida(id));
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/jogador/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RegistroPartidaDTO>> findActiveRegistroPartidaByJogador(@PathVariable Integer id)
			throws ActivationException {
		Jogador jog = new Jogador();
		jog.setId(id);
		List<RegistroPartidaDTO> list = jogo.findActiveByJogador(jog).stream().map(RegistroPartidaDTO::new).toList();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRegistroPartida(@PathVariable Integer id) {
		jogo.deleteRegistroPartida(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RegistroPartidaDTO>> findAllRegistroPartidas() {
		List<RegistroPartidaDTO> list = jogo.findAllRegistroPartidas().stream().map(RegistroPartidaDTO::new).toList();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public ResponseEntity<List<RankingDTO>> rankRegistroPartidas() {
		List<RankingDTO> list = jogo.rankRegistroPartida().stream()
				.map(x -> new RankingDTO(x.getJogador(), x.getPontuacao())).toList();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/ranking/configuracao/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RankingDTO>> rankRegistroPartidas(@PathVariable Integer id) {
		ConfiguracaoPartida cp = new ConfiguracaoPartida();
		cp.setId(id);
		List<RankingDTO> list = jogo.rankRegistroPartidaByConfiguracaoPartida(cp).stream()
				.map(x -> new RankingDTO(x.getJogador(), x.getPontuacao())).toList();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/responder", method = RequestMethod.POST)
	public ResponseEntity<Void> answerQuestion(@RequestBody RespostaDTO objDto) throws AuthorizationException,
	ObjectNotFoundException, ActivationException, IncorrectAlternativeException, InvalidNextQuestionException {
		jogo.answerQuestion(objDto.getRegistroPartida(), objDto.getAlternativa());
		return ResponseEntity.accepted().build();
	}
}
