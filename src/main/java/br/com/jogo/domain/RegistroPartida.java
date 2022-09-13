package br.com.jogo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
	@ManyToOne
	private ConfiguracaoPartida configuracaoPartida;
	@ManyToMany
	@JoinTable(name = "configuracaopartidas_questoes", joinColumns = {
			@JoinColumn(name = "partida_id") }, inverseJoinColumns = { @JoinColumn(name = "questao_id") })
	private Set<Questao> questoesRespondidas;

	public RegistroPartida(ConfiguracaoPartida configuracaoPartida) {
		this.configuracaoPartida = configuracaoPartida;
		this.momento = LocalDateTime.now();
		this.ativa = true;
		this.pontuacao = 0;
	}

	public RegistroPartida(Integer id, LocalDateTime momento, boolean ativa, int pontuacao, Questao ultimaQuestao, Set<Questao> questoesRespondidas) {
		this.id = id;
		this.momento = momento;
		this.ativa = ativa;
		this.pontuacao = pontuacao;
		this.questoesRespondidas = questoesRespondidas;
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

	public Questao getUltimaQuestao() {
		return ultimaQuestao;
	}

	public void addPontuacao(int pontos) {
		this.pontuacao += pontos;
	}
	
	public void setUltimaQuestao(Questao ultimaQuestao) {
		this.ultimaQuestao = ultimaQuestao;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public ConfiguracaoPartida getConfiguracaoPartida() {
		return configuracaoPartida;
	}

	public void setConfiguracaoPartida(ConfiguracaoPartida configuracaopartida) {
		this.configuracaoPartida = configuracaopartida;
	}

	public Set<Questao> getQuestoesRepondias() {
		return questoesRespondidas;
	}

	public void setQuestoesRespondidas(Set<Questao> questoes) {
		this.questoesRespondidas = questoes;
	}
	
	public void addQuestoes(Questao questao) {
		this.questoesRespondidas.add(questao);
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
