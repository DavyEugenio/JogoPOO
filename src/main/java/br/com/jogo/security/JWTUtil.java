package br.com.jogo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.jogo.security.exceptions.InvalidTokenException;
import br.com.jogo.services.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	@Autowired
	private UsuarioService usuarioService;

	private String generateToken(String subject) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key).compact();
	}

	public String generateAuthToken(String username) {
		usuarioService.registerAccess(username);
		return generateToken(username);
	}

	public String generatePasswordRecoveryToken(String username, String password) {
		return generateToken(username + "YN9YxSenhaYN9Yx" + password);
	}

	public boolean validToken(String token) throws InvalidTokenException {
		Claims claims = getClaims(token);
		if (claims != null) {
			String subject = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (subject != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getSubject(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) throws InvalidTokenException{
		try {
			return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new InvalidTokenException("Login expirado!");
		} catch (SignatureException e) {
			throw new InvalidTokenException("Assinatura não reconhecida!");
		}
	}
}