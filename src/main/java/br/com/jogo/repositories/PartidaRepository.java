package br.com.jogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.ConfiguracaoPartida;

@Repository
public interface PartidaRepository extends JpaRepository<ConfiguracaoPartida, Integer> {	
}
