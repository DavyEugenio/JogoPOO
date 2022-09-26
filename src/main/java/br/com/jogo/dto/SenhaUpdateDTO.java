package br.com.jogo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SenhaUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 8, message = "A nova senha deve conter no mínimo 8 caracteres")
	private String novaSenha;
	@Length(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	public SenhaUpdateDTO() {
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public String getSenha() {
		return senha;
	}
}
