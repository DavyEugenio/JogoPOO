package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Questao;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
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
		if (obj.getAlternativas() != null) {
			aux.setAlternativas(obj.getAlternativas());
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

	public Questao fromDTO(QuestaoDTO objDto) {
		return new Questao(objDto.getId(), objDto.getTexto(), objDto.getNivel(), null, null);
	}
	
	public Questao fromDTO(QuestaoNewDTO objDto) {
		return new Questao(objDto.getTexto(), objDto.getNivel(), null, null);
	}
}
