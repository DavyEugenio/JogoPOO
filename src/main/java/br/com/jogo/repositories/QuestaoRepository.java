package br.com.jogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {
}
