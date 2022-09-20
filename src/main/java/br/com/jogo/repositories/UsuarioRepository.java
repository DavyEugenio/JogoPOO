package br.com.jogo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
