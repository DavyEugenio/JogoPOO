package br.com.jogo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.jogo.services.EmailService;
import br.com.jogo.services.SmtpEmailService;

@Configuration
public class EmailConfig {
	@Bean
	EmailService emailService() {
		return new SmtpEmailService();
	}
}
