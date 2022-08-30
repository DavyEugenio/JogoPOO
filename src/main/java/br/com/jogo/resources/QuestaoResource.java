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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jogo.domain.Questao;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
import br.com.jogo.facade.Jogo;

@RestController
@RequestMapping(path = "/questoes")
public class QuestaoResource {
	@Autowired
	Jogo jogo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Questao> find(@PathVariable Integer id) {
		Questao obj = jogo.findQuestao(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@Valid @RequestBody QuestaoNewDTO objNewDto) {
		Questao obj = jogo.insertQuestao(objNewDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody QuestaoDTO objDto, @PathVariable Integer id) {
		jogo.updateQuestao(objDto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jogo.deleteQuestao(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<QuestaoDTO>> findAll() {
		List<Questao> list = jogo.findAllQuestoes();
		List<QuestaoDTO> listDto = list.stream().map(obj -> new QuestaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
