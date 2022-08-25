package br.com.jogo.domain;

import javax.persistence.Entity;

@Entity
public class Admin extends Usuario {

	private static final long serialVersionUID = 1L;

	public Admin() {
	}

	public Admin(Integer id, String nome, String nomeUsuario, String email, String senha) {
		super(id, nome, nomeUsuario, email, senha);
	}

	public Admin(String nome, String nomeUsuario, String email, String senha) {
		super(nome, nomeUsuario, email, senha);
	}
}
