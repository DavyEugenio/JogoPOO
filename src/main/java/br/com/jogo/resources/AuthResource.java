package br.com.jogo.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jogo.dto.EmailDTO;
import br.com.jogo.dto.SenhaDTO;
import br.com.jogo.dto.SenhaUpdateDTO;
import br.com.jogo.facade.Jogo;
import br.com.jogo.security.JWTUtil;
import br.com.jogo.security.UserSS;
import br.com.jogo.security.exceptions.InvalidTokenException;
import br.com.jogo.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private Jogo jogo;

	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws InvalidTokenException {
		UserSS user = UserService.authenticated();
		if (user != null) {
			System.out.println(user.getUsername());
			String token = jwtUtil.generateAuthToken(user.getUsername());
			response.addHeader("Authorization", "Bearer " + token);
			response.addHeader("access-control-expose-headers", "Authorization");
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		jogo.sendRecoveryPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/recoverPassword/{token}", method = RequestMethod.POST)
	public ResponseEntity<Void> recoverPassword(@Valid @RequestBody SenhaDTO senhaDTO, @PathVariable String token)
			throws InvalidTokenException {
		jogo.insertNewPassword(senhaDTO.getSenha(), token);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSenha(@Valid @RequestBody SenhaUpdateDTO objDto) throws InvalidTokenException {
		jogo.updateSenha(objDto.getNovaSenha(), objDto.getSenha());
		return ResponseEntity.noContent().build();
	}
}
