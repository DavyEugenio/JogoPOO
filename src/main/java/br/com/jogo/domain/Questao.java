package br.com.jogo.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Questao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String texto;
	private int nivel;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "questao_id")
	private Set<Alternativa> alternativas;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Questao() {
	}

	public Questao(String texto, int nivel, Categoria categoria, Set<Alternativa> alternativas) {
		this.texto = texto;
		this.nivel = nivel;
		this.categoria = categoria;
		this.alternativas = alternativas;
	}

	public Questao(Integer id, String texto, int nivel, Categoria categoria, Set<Alternativa> alternativas) {
		this(texto, nivel, categoria, alternativas);
		this.id = id;
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

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Set<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(Set<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@JsonIgnore
	public Alternativa getCorrectAlternative() {
		return this.alternativas.stream().filter(x -> x.isCorreta()).findFirst().get();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		return Objects.equals(id, other.id);
	}
}
