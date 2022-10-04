package br.com.jogo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jogo.domain.Jogador;
import br.com.jogo.dto.RankingDTO;
import br.com.jogo.dto.UsuarioDTO;
import br.com.jogo.dto.UsuarioNewDTO;
import br.com.jogo.facade.Jogo;
import br.com.jogo.security.exceptions.AuthorizationException;

@RestController
@RequestMapping(path = "/jogadores")
public class JogadorResource {
	@Autowired
	Jogo jogo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Jogador> find(@PathVariable Integer id) {
		Jogador obj = jogo.findJogador(id);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('JOGADOR')")
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Jogador> findByEmail(@RequestParam(value = "value") String email)
			throws AuthorizationException {
		Jogador obj = (Jogador) jogo.findJogadorByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@Valid @RequestBody UsuarioNewDTO objNewDto) {
		Jogador obj = jogo.insertJogador(objNewDto.toJogador());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
		Jogador obj = objDto.toJogador();
		obj.setId(id);
		jogo.updateJogador(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jogo.deleteJogador(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Jogador> list = jogo.findAllJogadores();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public ResponseEntity<List<RankingDTO>> rank() {
		List<Jogador> list = jogo.rankJogadores();
		List<RankingDTO> listDto = list.stream().map(obj -> new RankingDTO(obj, obj.getPontuacaoTotal()))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file)
			throws AuthorizationException {
		URI uri = jogo.uploadProfilePictureOfJogador(file);
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/picture", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProfilePicture() throws AuthorizationException {
		jogo.deleteProfilePictureOfJogador();
		return ResponseEntity.noContent().build();
	}
}
