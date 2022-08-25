package br.com.jogo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Embeddable
public class Inventario {
	
	@ManyToMany
	@JoinTable(name = "partidas_categorias", joinColumns = { @JoinColumn(name = "partida_id") }, inverseJoinColumns = {
			@JoinColumn(name = "categoria_id") })
	private List<Item> itens;
	@OneToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	
	public Inventario(Jogador jogador) {
		
		this.itens = new ArrayList<Item>();
		this.jogador = jogador;
	}
	
	public Inventario(List<Item> itens, Jogador jogador) {
		this.itens = itens;
		this.jogador = jogador;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jogador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventario other = (Inventario) obj;
		return jogador.equals(other.jogador);
	}
	
	
}
