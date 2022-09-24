package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.jogo.domain.Admin;
import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.Usuario;
import br.com.jogo.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 3, max = 120, message = "O tamanho de ser entre 3 e 120 caracters")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 3, max = 20, message = "O tamanho de ser entre 3 e 120 caracters")
	private String nomeUsuario;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido!")
	private String email;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.nomeUsuario = obj.getNomeUsuario();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
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
	
	public Jogador toJogador() {
		return new Jogador(id, nome, nomeUsuario, email, null, 0, 0, 0,
				0, null, 0, null);
	}
	
	public Admin toAdmin() {
		return new Admin(id, nome, nomeUsuario, email, null);
	}
}
