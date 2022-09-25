package br.com.jogo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;
import br.com.jogo.repositories.QuestaoRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class QuestaoService {

	@Autowired
	private QuestaoRepository repository;

	public Questao find(Integer id) {
		Optional<Questao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Questao.class.getName()));
	}

	@Transactional
	public Questao insert(Questao obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Questao update(Questao obj) {
		Questao newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Questao obj, Questao aux) {
		if (obj.getTexto() != null) {
			aux.setTexto(obj.getTexto());
		}
		if (obj.getNivel() != 0) {
			aux.setNivel(obj.getNivel());
		}
		if (obj.getCategoria() != null) {
			aux.setCategoria(obj.getCategoria());
		}
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma questão que possui alternativas");
		} catch (ObjectNotFoundException e) {
		}
	}

	public List<Questao> findAll() {
		return repository.findAll();
	}

	public Questao findByAlternativa(Alternativa alternativa) {
		Optional<Questao> obj = repository.findByAlternativas(alternativa);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id da alternativa: " + alternativa.getId() + ", Tipo: " + Questao.class.getName()));
	}

	private Set<Integer> SetOfIdsQuestoes(Set<Questao> questoes) {
		Set<Integer> ids;
		if (questoes != null && !questoes.isEmpty()) {
			ids = questoes.stream().map(obj -> obj.getId()).collect(Collectors.toSet());
		} else {
			ids = Set.of(0);
		}
		return ids;
	}

	public Questao findOneNotIn(Set<Questao> questoes) throws ObjectNotFoundException {
		Optional<Questao> obj = repository.findDistinctFirstByIdNotIn(SetOfIdsQuestoes(questoes));
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado, Tipo: " + Questao.class.getName()));
	}

	public Questao findOneByCategoria(Set<Categoria> categorias, Set<Questao> questoes) throws ObjectNotFoundException {
		Optional<Questao> obj = repository.findDistinctFirstByCategoriaNotInAndIdNotIn(categorias,
				SetOfIdsQuestoes(questoes));
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado, Tipo: " + Questao.class.getName()));
	}

	public Questao findOneByNivel(int nivel, Set<Questao> questoes) throws ObjectNotFoundException {
		Optional<Questao> obj = repository.findDistinctFirstByNivelAndIdNotIn(nivel, SetOfIdsQuestoes(questoes));
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado, Tipo: " + Questao.class.getName()));
	}

	public Questao findOneByNivelAndCategoria(int nivel, Set<Categoria> categorias, Set<Questao> questoes) throws ObjectNotFoundException {
		Optional<Questao> obj = repository.findDistinctFirstByNivelAndCategoriaNotInAndIdNotIn(nivel,
				categorias, SetOfIdsQuestoes(questoes));
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado, Tipo: " + Questao.class.getName()));
	}
}
