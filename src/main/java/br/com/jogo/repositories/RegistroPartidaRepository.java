package br.com.jogo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.RegistroPartida;

@Repository
public interface RegistroPartidaRepository extends JpaRepository<RegistroPartida, Integer> {
	public List<RegistroPartida> findByAtivaTrueAndJogador(Jogador jogador);
}
