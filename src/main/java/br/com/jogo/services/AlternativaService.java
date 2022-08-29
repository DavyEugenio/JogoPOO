package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.repositories.AlternativaRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class AlternativaService {

	@Autowired
	private AlternativaRepository repository;

	public Alternativa find(Integer id) {
		Optional<Alternativa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Alternativa.class.getName()));
	}

	@Transactional
	public Alternativa insert(Alternativa obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Alternativa update(Alternativa obj) {
		Alternativa newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Alternativa obj, Alternativa aux) {
		if (obj.getTexto() != null) {
			aux.setTexto(obj.getTexto());
		}
			aux.setCorreta(obj.isCorreta());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria relacionada");
		} catch (ObjectNotFoundException e) {
		}
	}

	public List<Alternativa> findAll() {
		return repository.findAll();
	}
	/*
	 * public Alternativa fromDTO(AlternativaDTO catDto) { return new
	 * Alternativa(catDto.getId(), catDto.getDescricao()); }
	 */
}
