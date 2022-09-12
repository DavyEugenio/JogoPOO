package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Objects;

public class AlternativaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String texto;
	private boolean correta;

	public AlternativaNewDTO() {
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correta, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlternativaNewDTO other = (AlternativaNewDTO) obj;
		return correta == Objects.equals(texto, other.texto);
	}
}
