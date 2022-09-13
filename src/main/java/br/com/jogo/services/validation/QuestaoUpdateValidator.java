package br.com.jogo.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.jogo.dto.AlternativaNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.resources.exception.FieldMessage;

public class QuestaoUpdateValidator implements ConstraintValidator<QuestaoUpdate, QuestaoDTO> {

	@Override
	public void initialize(QuestaoUpdate ann) {
	}

	@Override
	public boolean isValid(QuestaoDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Set<AlternativaNewDTO> alternativas = objDto.getAlternativas();
		if (alternativas != null) {
			if (alternativas.size() != 4) {
				list.add(new FieldMessage("alternativas", "Quantidade diferente de 4 ou repetições!"));
			}
			List<Integer> corretas = alternativas.stream().filter(x -> x.isCorreta()).map(x -> 0).toList();
			if (corretas.size() < 1) {
				list.add(new FieldMessage("alternativas", "Ausência de alternativa correta!"));
			}
			if (corretas.size() > 1) {
				list.add(new FieldMessage("alternativas", "Mais de uma alternativa correta!"));
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}