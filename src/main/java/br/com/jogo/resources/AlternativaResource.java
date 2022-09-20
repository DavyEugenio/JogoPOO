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
}
