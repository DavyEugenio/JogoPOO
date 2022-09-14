package br.com.jogo.dto;

import br.com.jogo.domain.Alternativa;

public class AlternativaDTO extends AlternativaBasicData {

	public AlternativaDTO(Alternativa obj) {
		super(obj.getTexto());
	}
}
