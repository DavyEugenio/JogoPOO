package br.com.jogo.dto;

import java.io.Serializable;

public class ItemJogadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer item;
	private int quantidade;

	public ItemJogadorDTO(Integer item, int quantidade) {
		this.item = item;
		this.quantidade = quantidade;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
