package br.com.jogo.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.jogo.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	protected String htmlFromTemplateConfirmationUsuario(Usuario u) {
		Context context = new Context();
		context.setVariable("usuario", u);
		return templateEngine.process("email/confirmacaoCadastro", context);
	}

	protected String htmlFromTemplateRecoverPassword(Usuario u, String passwordRecoveryURL) {
		Context context = new Context();
		context.setVariable("usuario", u);
		context.setVariable("passwordRecoveryURL", passwordRecoveryURL);
		return templateEngine.process("email/recuperacaoSenha", context);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario u, String subject, String text) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(u.getEmail());
		sm.setFrom(sender);
		sm.setSubject(subject);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(text);
		return sm;
	}

	protected MimeMessage prepareMimeMessageFromUsuario(Usuario u, String subject, String text)
			throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(u.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject(subject);
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(text, true);
		return mimeMessage;
	}

	@Override
	public void sendUserConfirmationEmail(Usuario u) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(u, "Confirmação de Cadastro", u.toString());
		sendEmail(sm);
	}

	@Override
	public void sendUserConfirmationHtmlEmail(Usuario u) {
		try {
			MimeMessage mm = prepareMimeMessageFromUsuario(u, "Confirmação de Cadastro",
					htmlFromTemplateConfirmationUsuario(u));
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendUserConfirmationEmail(u);
		}
	}

	@Override
	public void sendPassworRecoveyURLEmail(Usuario usuario, String passwordRecoveryURL) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(usuario, "Solicitação de Nova Senha",
				"Endereço para criação de nova senha: " + passwordRecoveryURL);
		sendEmail(sm);
	}

	@Override
	public void sendPassworRecoveyURLHtmlEmail(Usuario u, String passwordRecoveryURL) {
		try {
			MimeMessage mm = prepareMimeMessageFromUsuario(u, "Solicitação de Nova Senha",
					htmlFromTemplateRecoverPassword(u, passwordRecoveryURL));
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendPassworRecoveyURLEmail(u, passwordRecoveryURL);
		}
	}
}
