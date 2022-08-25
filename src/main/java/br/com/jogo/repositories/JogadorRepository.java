package br.com.jogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {	
}
