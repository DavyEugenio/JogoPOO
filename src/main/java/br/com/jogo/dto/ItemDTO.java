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

	public void setId(Integer id) {
		this.id = id;
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
