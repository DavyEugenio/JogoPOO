package br.com.jogo.dto;

import java.io.Serializable;

import br.com.jogo.domain.Questao;

public class QuestaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String texto;
	private int nivel;
	private CategoriaDTO categoria;

	public QuestaoDTO(Questao obj) {
		super();
		this.id = obj.getId();
		this.texto = obj.getTexto();
		this.nivel = obj.getNivel();
		this.categoria = new CategoriaDTO(obj.getCategoria());
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

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}
}
