package br.com.jogo.dto;

import br.com.jogo.domain.Alternativa;

public class AlternativaDTO extends AlternativaBasicData {
	
	private Integer id;
	
	public AlternativaDTO(Alternativa obj) {
		super(obj.getTexto());
		this.id = obj.getId();
	}

	public Integer getId() {
		return id;
	}
}
