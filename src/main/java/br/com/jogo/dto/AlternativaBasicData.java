package br.com.jogo.dto;

import java.util.Objects;

public abstract class AlternativaBasicData {
	private String texto;

	public AlternativaBasicData(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlternativaBasicData other = (AlternativaBasicData) obj;
		return Objects.equals(texto, other.texto);
	}

}
