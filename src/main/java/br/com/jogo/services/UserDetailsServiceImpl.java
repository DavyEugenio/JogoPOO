package br.com.jogo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Usuario;
import br.com.jogo.repositories.UsuarioRepository;
import br.com.jogo.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = repo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getRole());
	}
}
