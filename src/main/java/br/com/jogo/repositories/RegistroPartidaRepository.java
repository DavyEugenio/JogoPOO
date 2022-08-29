package br.com.jogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.RegistroPartida;

@Repository
public interface RegistroPartidaRepository extends JpaRepository<RegistroPartida, Integer> {
}
