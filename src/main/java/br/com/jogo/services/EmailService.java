package br.com.jogo.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.jogo.domain.Usuario;

public interface EmailService {

	void sendUserConfirmationEmail(Usuario u);

	void sendEmail(SimpleMailMessage msg);

	void sendUserConfirmationHtmlEmail(Usuario u);

	void sendHtmlEmail(MimeMessage msg);

	void sendPassworRecoveyURLEmail(Usuario usuario, String passwordRecoveryURL);

	void sendPassworRecoveyURLHtmlEmail(Usuario usuario, String passwordRecoveryURL);
}
