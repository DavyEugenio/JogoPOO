package br.com.jogo.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.jogo.domain.Categoria;
import br.com.jogo.dto.CategoriaDTO;
import br.com.jogo.repositories.CategoriaRepository;
import br.com.jogo.resources.exception.FieldMessage;

public class CategoriaUpdateValidator implements ConstraintValidator<CategoriaUpdate, CategoriaDTO> {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CategoriaRepository repo;

	@Override
	public void initialize(CategoriaUpdate ann) {
	}

	@Override
	public boolean isValid(CategoriaDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();

		Categoria aux = repo.findByNome(objDto.getNome());
		if (aux != null && aux.getId() != uriId) {
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