package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AdminNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 3, max = 120, message = "O tamanho de ser entre 5 e 120 caracters")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 3, max = 20, message = "O tamanho de ser entre 5 e 120 caracters")
	private String nomeUsuario;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido")
	private String email;

	public AdminNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
