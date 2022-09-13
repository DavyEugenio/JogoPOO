package br.com.jogo.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alternativa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String texto;
	private boolean correta;

	public Alternativa() {
	}

	public Alternativa(String texto, boolean correta) {
		this.texto = texto;
		this.correta = correta;
	}

	public Alternativa(Integer id, String texto, boolean correta) {
		this.id = id;
		this.texto = texto;
		this.correta = correta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Alternativa [id=" + id + ", texto=" + texto + ", correta=" + correta + "]";
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alternativa other = (Alternativa) obj;
		return Objects.equals(id, other.id) && Objects.equals(texto, other.texto);
	}

}
