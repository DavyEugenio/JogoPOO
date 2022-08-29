package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Usuario;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;


@Service
public class UsuarioService {
	/*
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	@Transactional
	public Usuario insert(Usario obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Usuario update(Usuario obj) {
		Usuario newCat = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Usuario obj, Usuario aux) {
		if (obj.getDescricao() != null) {
			aux.setDescricao(obj.getDescricao());
		}
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui estabelecimentos");
		} catch (ObjectNotFoundException e) {
		}
	}
	
	public List<Usuario> findAll() {
		return repository.findAllByOrderByDescricaoAsc();
	}

	public Usuario fromDTO(UsuarioDTO catDto) {
		return new Usuario(catDto.getId(), catDto.getDescricao());
	}*/
}
