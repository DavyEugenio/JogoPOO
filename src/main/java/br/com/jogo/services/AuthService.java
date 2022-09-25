package br.com.jogo.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Usuario;
import br.com.jogo.security.JWTUtil;
import br.com.jogo.security.exceptions.InvalidTokenException;

@Service
public class AuthService {

	@Autowired
	private JWTUtil jwtUtil;

	@Value("${domain.url}")
	private String domainURL;

	public String generateRecoveryPasswordUrl(Usuario obj, String encodedPassword) {
		return domainURL + "/pages/criarnovasenha/?rpt="
				+ jwtUtil.generatePasswordRecoveryToken(obj.getEmail(), obj.getSenha()) + "&email="
				+ obj.getEmail();
	}

	public String[] recoverEmailAndPasswordbyToken(String token) {
		if (jwtUtil.validToken(token)) {
			return jwtUtil.getSubject(token).split("YN9YxSenhaYN9Yx");
		} else {
			throw new InvalidTokenException("Tempo Expirado");
		}
	}
	
	public String newRandonPassword() {
		char[] vet = new char[8];
		for (int i = 0; i < 8; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		Random random = new Random();
		int opt = random.nextInt(3);

		if (opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}
}
