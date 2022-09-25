package br.com.jogo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.Usuario;
import br.com.jogo.repositories.UsuarioRepository;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario findByEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, E-mail: " + email + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public void updatePassword(Usuario obj, String password) {
		obj = find(obj.getId());
		obj.setSenha(password);
		repository.save(obj);
	}

	public void registerAccess(String email) {
		Usuario obj = findByEmail(email);
		if (obj instanceof Jogador) {
			((Jogador) obj).registerAccess();
			repository.save(obj);
		}
		repository.save(obj);
	}
}
