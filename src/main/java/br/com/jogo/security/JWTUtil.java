package br.com.jogo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;

	private String generateToken(String subject) {
		
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key).compact();
	}
	
	public String generateAuthToken(String username) {
		return generateToken(username);
	}

	public String generatePasswordRecoveryToken(String username, String password) {
		return generateToken(username + "YN9YxSenhaYN9Yx" + password);
	}

	public boolean validToken(String token) {
		System.out.println("Validação");
		System.out.println(token);
		Claims claims = getClaims(token);
		System.out.println(getClaims(token));
		if (claims != null) {
			System.out.println(getClaims(token).getSubject());
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

	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
	}
}