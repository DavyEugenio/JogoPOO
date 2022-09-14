package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.Item;

public class ItemNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String funcao;
	private int raridade;
	private int preco;
	private int penalidade;

	public ItemNewDTO() {
	}

	public ItemNewDTO(String nome, String funcao, int raridade, int preco, int penalidade) {
		super();
		this.nome = nome;
		this.funcao = funcao;
		this.raridade = raridade;
		this.preco = preco;
		this.penalidade = penalidade;
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
		return new Item(nome, funcao, raridade, preco, penalidade);
	}
}
