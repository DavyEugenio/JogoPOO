package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;

public class QuestaoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String texto;
	private int nivel;
	private Integer categoria;
	private Set<AlternativaNewDTO> alternativas;

	public QuestaoNewDTO() {
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

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Set<AlternativaNewDTO> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(Set<AlternativaNewDTO> alternativas) {
		this.alternativas = alternativas;
	}

}
