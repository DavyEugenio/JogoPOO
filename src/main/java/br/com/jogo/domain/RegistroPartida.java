package br.com.jogo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class RegistroPartida implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime momento;
	@ManyToOne
	@JoinColumn(name = "ultimaquestao_id")
	private Questao ultimaQuestao;
	private boolean ativa;
	private int pontuacao;
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	@ManyToMany
	@JoinTable(name = "configuracaopartidas_questoes", joinColumns = { @JoinColumn(name = "partida_id") }, inverseJoinColumns = {
			@JoinColumn(name = "questao_id") })
	private List<Questao> questoes;

	public RegistroPartida() {
		this.momento = LocalDateTime.now();
		this.ativa = true;
		this.pontuacao = 0;
	}

	public RegistroPartida(Integer id, LocalDateTime momento, boolean ativa, int pontuacao, Questao ultimaQuestao) {
		this.id = id;
		this.momento = momento;
		this.ativa = ativa;
		this.pontuacao = pontuacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getMomento() {
		return momento;
	}

	public void setMomento(LocalDateTime momento) {
		this.momento = momento;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroPartida other = (RegistroPartida) obj;
		return Objects.equals(id, other.id);
	}

}
