package br.com.jogo.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {
	public Questao findByAlternativas(Alternativa alternativa);

	public Questao findDistinctFirstByIdNotIn(Set<Questao> questoes);

	public Questao findDistinctFirstByCategoriaNotInAndIdNotIn(Set<Categoria> categorias, Set<Questao> questoes);

	public Questao findDistinctFirstByNivelAndIdNotIn(int nivel, Set<Questao> questoes);

	public Questao findDistinctFirstByNivelAndCategoriaNotInAndIdNotIn(int nivel, Set<Categoria> categorias,
			Set<Questao> questoes);
}
