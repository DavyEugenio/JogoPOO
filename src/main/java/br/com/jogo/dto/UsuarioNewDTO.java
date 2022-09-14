package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.jogo.domain.Admin;
import br.com.jogo.domain.Jogador;
import br.com.jogo.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDTO implements Serializable {

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
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 8, message = "O tamanho de ser igual ou superior a 8 caracters")
	private String senha;

	public UsuarioNewDTO(String nome, String nomeUsuario, String email, String senha) {
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	public Jogador toJogador() {
		return new Jogador(nome, nomeUsuario, email, senha);
	}
	
	public Admin toAdmin() {
		return new Admin(nome, nomeUsuario, email, senha);
	}
}
