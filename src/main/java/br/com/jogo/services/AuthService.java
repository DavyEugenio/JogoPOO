package br.com.jogo.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	/*@Autowired
	private UsuarioRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	/*@Autowired
	private EmailService emailService;
	*/
	private Random rand = new Random();
/*
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}*/

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 1) {
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 2) {
			return (char) (rand.nextInt(26) + 65);
		} else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
