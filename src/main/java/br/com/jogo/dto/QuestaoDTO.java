package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;

import br.com.jogo.domain.Questao;

public class QuestaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String texto;
	private int nivel;
	private Integer categoria;
	private Set<AlternativaNewDTO> alternativas;

	public QuestaoDTO() {
	}

	public QuestaoDTO(Questao obj) {
		super();
		this.id = obj.getId();
		this.texto = obj.getTexto();
		this.nivel = obj.getNivel();
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
