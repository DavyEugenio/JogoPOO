package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.RegistroPartida;
import br.com.jogo.repositories.RegistroPartidaRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class RegistroPartidaService {

	@Autowired
	private RegistroPartidaRepository repository;

	public RegistroPartida find(Integer id) {
		Optional<RegistroPartida> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + RegistroPartida.class.getName()));
	}

	@Transactional
	public RegistroPartida insert(RegistroPartida obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public RegistroPartida update(RegistroPartida obj) {
		RegistroPartida newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(RegistroPartida obj, RegistroPartida aux) {
		aux.setAtiva(obj.isAtiva());
		aux.setConfiguracaoPartida(obj.getConfiguracaoPartida());
		aux.setPontuacao(obj.getPontuacao());
		aux.setQuestoesRespondidas(obj.getQuestoesRepondias());
		aux.setUltimaQuestao(obj.getUltimaQuestao());
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

	public List<RegistroPartida> findAll() {
		return repository.findAll();
	}

	public List<RegistroPartida> findAtivaByJogador(Jogador obj) {
		return repository.findByAtivaTrueAndJogador(obj);
	}
}
