package br.com.jogo.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemJogador implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private ItemJogadorPK id;
	private int quantidade;

	public ItemJogador(Jogador jogador, Item item, int quantidade) {
		id = new ItemJogadorPK(jogador, item);
		this.quantidade = quantidade;
	}

	public ItemJogadorPK getId() {
		return id;
	}

	public void setId(ItemJogadorPK id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@JsonIgnore
	public Item getItem() {
		return id.getItem();
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
		ItemJogador other = (ItemJogador) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getItem().getNome());
		builder.append(", Qtd: ");
		builder.append(getQuantidade());
		builder.append("\n");
		return builder.toString();
	}

}
