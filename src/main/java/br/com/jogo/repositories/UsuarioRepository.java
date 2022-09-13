package br.com.jogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);

	public Usuario findByNomeUsuario(String nomeUsuario);
}
