package br.com.jogo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jogo.domain.Categoria;
import br.com.jogo.dto.CategoriaNewDTO;
import br.com.jogo.repositories.CategoriaRepository;
import br.com.jogo.resources.exception.FieldMessage;

public class CategoriaInsertValidator implements ConstraintValidator<CategoriaInsert, CategoriaNewDTO> {

	@Autowired
	private CategoriaRepository repo;

	@Override
	public void initialize(CategoriaInsert ann) {
	}

	@Override
	public boolean isValid(CategoriaNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Categoria aux = repo.findByNome(objDto.getNome());
		if (aux != null) {
			list.add(new FieldMessage("nome", "Nome já existente no sistema!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}