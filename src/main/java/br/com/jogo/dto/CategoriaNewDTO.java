package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.jogo.domain.Categoria;
import br.com.jogo.services.validation.CategoriaInsert;

@CategoriaInsert
public class CategoriaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Preenchimento obrigatório, não nulo e não-branco!")
	@Length(min = 5, max = 80, message = "O tamanho de ser entre 5 e 80 caracters")
	private String nome;

	public CategoriaNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public Categoria toEntity() {
		return new Categoria(nome);
	}
}