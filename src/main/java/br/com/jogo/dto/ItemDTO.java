package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.Item;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String funcao;
	private int raridade;
	private int preco;
	private int penalidade;

	public ItemDTO(Item item) {
		this.id = item.getId();
		this.nome = item.getNome();
		this.funcao = item.getFuncao();
		this.raridade = item.getRaridade();
		this.preco = item.getPreco();
		this.penalidade = item.getPenalidade();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public int getPreco() {
		return preco;
	}

	public int getPenalidade() {
		return penalidade;
	}

	public int getRaridade() {
		return raridade;
	}

	public Item toEntity() {
		return new Item(id, nome, funcao, raridade, preco, penalidade);
	}
}
