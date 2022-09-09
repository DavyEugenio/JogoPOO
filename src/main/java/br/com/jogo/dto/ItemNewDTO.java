package br.com.jogo.dto;

import java.io.Serializable;

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public int getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(int penalidade) {
		this.penalidade = penalidade;
	}

	public int getRaridade() {
		return raridade;
	}

	public void setRaridade(int raridade) {
		this.raridade = raridade;
	}

}
