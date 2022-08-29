package br.com.jogo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String funcao;
	private int raridade;
	private int preco;
	private int penalidade;

	public Item(String nome, String funcao, int raridade, int preco, int penalidade) {
		this.nome = nome;
		this.funcao = funcao;
		this.raridade = raridade;
		this.preco = preco;
		this.penalidade = penalidade;
	}

	public Item(Integer id, String nome, String funcao, int raridade, int preco, int penalidade) {
		this(nome, funcao, raridade, preco, penalidade);
		this.id = id;
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

	public int getRaridade() {
		return raridade;
	}

	public void setRaridade(int raridade) {
		this.raridade = raridade;
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

}
