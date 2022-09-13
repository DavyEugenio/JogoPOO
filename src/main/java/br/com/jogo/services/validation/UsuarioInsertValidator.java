package br.com.jogo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jogo.domain.Usuario;
import br.com.jogo.dto.UsuarioNewDTO;
import br.com.jogo.repositories.UsuarioRepository;
import br.com.jogo.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente no sistema!"));
		}
		aux = repo.findByNomeUsuario(objDto.getNomeUsuario());
		if (aux != null) {
			list.add(new FieldMessage("nomeUsuario", "Nome de usuário já existente no sistema!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}