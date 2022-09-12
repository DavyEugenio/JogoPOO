package br.com.jogo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.dto.ConfiguracaoPartidaNewDTO;
import br.com.jogo.repositories.ConfiguracaoPartidaRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class ConfiguracaoPartidaService {

	@Autowired
	private ConfiguracaoPartidaRepository repository;

	public ConfiguracaoPartida find(Integer id) {
		Optional<ConfiguracaoPartida> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + ConfiguracaoPartida.class.getName()));
	}

	@Transactional
	public ConfiguracaoPartida insert(ConfiguracaoPartida obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public ConfiguracaoPartida update(ConfiguracaoPartida obj) {
		ConfiguracaoPartida newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(ConfiguracaoPartida obj, ConfiguracaoPartida aux) {
		if (obj.getJogador() != null) {
			aux.setJogador(obj.getJogador());
		}
		if (obj.getNivel() != 0) {
			aux.setNivel(obj.getNivel());
		}
		aux.setPredefinida(obj.isPredefinida());
		if (obj.getQuestoes() != null) {
			aux.setQuestoes(obj.getQuestoes());
		}
		if (obj.getCategorias() == null) {
			aux.setCategorias(Set.copyOf(obj.getQuestoes().stream().map(q -> q.getCategoria()).toList()));
		} else {
			aux.setCategorias(obj.getCategorias());
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

	public List<ConfiguracaoPartida> findAll() {
		return repository.findAll();
	}

	public ConfiguracaoPartida fromDTO(ConfiguracaoPartidaNewDTO objDto) {
		return new ConfiguracaoPartida(null, objDto.getNivel());
	}

}
