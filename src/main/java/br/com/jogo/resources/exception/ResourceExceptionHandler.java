package br.com.jogo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jogo.security.exceptions.AuthorizationException;
import br.com.jogo.security.exceptions.InvalidTokenException;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.FileException;
import br.com.jogo.services.exceptions.IncorrectAlternativeException;
import br.com.jogo.services.exceptions.InvalidNextQuestionException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação", "Campos não preenchidos corretamente", request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e,
			HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro de validação", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<StandardError> invalidTokenException(InvalidTokenException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Token inválido", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> fileException(FileException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro ao tentar acesar a imagem", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(InvalidNextQuestionException.class)
	public ResponseEntity<StandardError> invalidNextQuestionException(InvalidNextQuestionException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), 210,
				"Vitória", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(210).body(err);
	}
	
	@ExceptionHandler(IncorrectAlternativeException.class)
	public ResponseEntity<StandardError> incorrectAlternativeException(IncorrectAlternativeException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_ACCEPTABLE.value(),
				"Resposta Incorreta", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}
}
