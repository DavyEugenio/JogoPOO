package br.com.jogo.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {
	public Optional<Questao> findByAlternativas(Alternativa alternativa);

	public Optional<Questao> findDistinctFirstByIdNotIn(Set<Integer> questoes);

	public Optional<Questao> findDistinctFirstByCategoriaNotInAndIdNotIn(Set<Categoria> categorias, Set<Integer> questoes);

	public Optional<Questao> findDistinctFirstByNivelAndIdNotIn(int nivel, Set<Integer> questoes);

	public Optional<Questao> findDistinctFirstByNivelAndCategoriaNotInAndIdNotIn(int nivel, Set<Categoria> categorias,
			Set<Integer> questoes);
}
