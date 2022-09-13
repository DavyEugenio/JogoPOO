package br.com.jogo.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.facade.Jogo;

@RestController
@RequestMapping(path = "/alternativas")
public class AlternativaResource {
	@Autowired
	Jogo jogo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Alternativa> find(@PathVariable Integer id) {
		Alternativa obj = jogo.findAlternativa(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@Valid @RequestBody AlternativaNewDTO objNewDto) {
		Alternativa obj = jogo.insertAlternativa(objNewDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj.getId());
	}
		 * 
		 * @RequestMapping(value = "/{id}", method = RequestMethod.PUT) public
		 * ResponseEntity<Void> update(@Valid @RequestBody AlternativaDTO
		 * objDto, @PathVariable Integer id) { jogo.updateAlternativa(objDto); return
		 * ResponseEntity.noContent().build(); }
		 * 
		 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public
		 * ResponseEntity<Void> delete(@PathVariable Integer id) {
		 * jogo.deleteAlternativa(id); return ResponseEntity.noContent().build(); }
		 * 
		 * @RequestMapping(method = RequestMethod.GET) public
		 * ResponseEntity<List<AlternativaDTO>> findAll() { List<Alternativa> list =
		 * jogo.findAllQuestoes(); List<AlternativaDTO> listDto = list.stream().map(obj
		 * -> new AlternativaDTO(obj)).collect(Collectors.toList()); return
		 * ResponseEntity.ok().body(listDto); }
		 */
}
