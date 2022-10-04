package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.jogo.domain.Categoria;
import br.com.jogo.services.validation.CategoriaUpdate;

@CategoriaUpdate
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "Preenchimento obrigatório, não nulo e não-branco!")
	@Length(min = 5, max = 80, message = "O tamanho de ser entre 5 e 80 caracters")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria toEntity() {
		return new Categoria(id, nome);
	}
}