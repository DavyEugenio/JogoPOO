package br.com.jogo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jogo.domain.Admin;
import br.com.jogo.domain.Jogador;
import br.com.jogo.dto.UsuarioDTO;
import br.com.jogo.dto.UsuarioNewDTO;
import br.com.jogo.facade.Jogo;

@RestController
@RequestMapping(path = "/admins")
public class AdminResource {
	@Autowired
	Jogo jogo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Admin> find(@PathVariable Integer id) {
		Admin obj = jogo.findAdmin(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Admin> findByEmail(@RequestParam(value = "value") String email) {
		Admin obj = (Admin) jogo.findUsuarioByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@Valid @RequestBody UsuarioNewDTO objNewDto) {
		Admin obj = jogo.insertAdmin(objNewDto.toAdmin());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
		Admin obj = objDto.toAdmin();
		obj.setId(id);
		jogo.updateAdmin(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jogo.deleteAdmin(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Admin> list = jogo.findAllAdmins();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
