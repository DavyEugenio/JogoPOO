package br.com.jogo.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;

@Entity
public class ConfiguracaoPartida implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int nivel;
	private boolean predefinida;
	@OneToMany
	@JoinColumn(name = "configuracaopartida_id")
	private List<RegistroPartida> registroPartidas;
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	@ManyToMany
	@JoinTable(name = "configuracaopartidas_questoes", joinColumns = { @JoinColumn(name = "partida_id") }, inverseJoinColumns = {
			@JoinColumn(name = "questao_id") })
	private List<Questao> questoes;
	@ManyToMany
	@JoinTable(name = "configuracaopartidas_categorias", joinColumns = { @JoinColumn(name = "partida_id") }, inverseJoinColumns = {
			@JoinColumn(name = "categoria_id") })
	private List<Categoria> categorias;

	public ConfiguracaoPartida() {
		this.categorias = new ArrayList<>();
	}
	
	public ConfiguracaoPartida(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ConfiguracaoPartida(Integer id, int nivel) {
		this.id = id;
		this.nivel = nivel;
	}

	public ConfiguracaoPartida(Integer id, int nivel, Jogador jogador) {
		this.id = id;
		this.nivel = nivel;
		this.jogador = jogador;
	}

	public ConfiguracaoPartida(Integer id, List<Questao> questoes) {
		this.id = id;
		this.questoes = questoes;
		this.categorias = questoes.stream().map(q -> q.getCategoria()).toList();
	}

	public ConfiguracaoPartida(Integer id, int nivel, Jogador jogador, List<Questao> questoes) {
		this.id = id;
		this.nivel = nivel;
		this.jogador = jogador;
		this.questoes = questoes;
		this.predefinida = true;
		this.categorias = questoes.stream().map(q -> q.getCategoria()).toList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public boolean isPredefinida() {
		return predefinida;
	}

	public void setPredefinida(boolean predefinida) {
		this.predefinida = predefinida;
	}

	public List<RegistroPartida> getRegistroPartidas() {
		return registroPartidas;
	}

	public void setRegistroPartidas(List<RegistroPartida> registroPartidas) {
		this.registroPartidas = registroPartidas;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		ConfiguracaoPartida other = (ConfiguracaoPartida) obj;
		return Objects.equals(id, other.id);
	}

}
