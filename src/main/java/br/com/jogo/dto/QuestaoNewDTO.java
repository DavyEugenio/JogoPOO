package br.com.jogo.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestaoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String texto;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "O valor mínimo é 1", value = 1)
	@Max(message = "O valor máximo é 5", value = 5)
	private int nivel;
	@NotNull(message = "Preenchimento obrigatório")
	@Min(message = "ID inválido", value = 1)
	private Integer categoria;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(max = 4, min = 4, message = "A quantidade de alternativas permitida é 4")
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
