package br.com.jogo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Jogador;
import br.com.jogo.repositories.JogadorRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository repository;

	public Jogador find(Integer id) {
		Optional<Jogador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Jogador.class.getName()));
	}

	@Transactional
	public Jogador insert(Jogador obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Jogador update(Jogador obj) {
		Jogador newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Jogador obj, Jogador aux) {
		if (obj.getEmail() != null) {
			aux.setEmail(obj.getEmail());
		}
		if (obj.getNome() != null) {
			aux.setNome(obj.getNome());
		}
		if (obj.getNomeUsuario() != null) {
			aux.setNomeUsuario(obj.getNomeUsuario());
		}
		aux.setSaldo(obj.getSaldo());
		if (obj.getUltimoAcesso() != null) {
			aux.setUltimoAcesso(obj.getUltimoAcesso());
		}
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um jogador relacionado");
		} catch (ObjectNotFoundException e) {
		}
	}
	
	public List<Jogador> rank() {
		return repository.findAllByOrderByPontuacaoTotalDesc();
	}
	
	public List<Jogador> findAll() {
		return repository.findAll();
	}

	public void registerAccess(Integer id) {
		LocalDate today = LocalDate.now();
		Jogador obj = find(id);
		if (obj.getUltimoAcesso() == today.minusDays(1)) {
			obj.addQtdAcesso();
		} else if (obj.getUltimoAcesso() != today) {
			obj.setQtdAcessosContinuo(0);
		}
		repository.save(obj);
	}
}
