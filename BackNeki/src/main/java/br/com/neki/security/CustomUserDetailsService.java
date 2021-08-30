package br.com.neki.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.neki.entities.Usuario;
import br.com.neki.repositories.UsuarioRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Usuario usuario = getUser(() -> usuarioRepository.findByEmail(email));
		return usuario;
	}

	public UserDetails pegarUsuarioPorId(Long id) {
		Usuario usuario = getUser(() -> usuarioRepository.findById(id));
		return usuario;
	}

	private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

}
