package br.com.jogo.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemJogadorPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	public ItemJogadorPK(Jogador jogador, Item item) {
		super();
		this.jogador = jogador;
		this.item = item;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(item, jogador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemJogadorPK other = (ItemJogadorPK) obj;
		return Objects.equals(item, other.item) && Objects.equals(jogador, other.jogador);
	}

}
