package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.jogo.services.validation.CategoriaInsert;

@CategoriaInsert
public class CategoriaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho de ser entre 5 e 80 caracters")
	private String nome;

	public CategoriaNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}