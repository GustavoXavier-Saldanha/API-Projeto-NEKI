package br.com.neki.mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.UsuarioGeralDTO;

@Component
public class UsuarioMapper {

	public Usuario toEntity(UsuarioGeralDTO dto) {
		Usuario usuario = new Usuario();
		usuario .setNome(dto.getNome());
		usuario .setEmail(dto.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada  = encoder.encode(dto.getSenha());
		usuario .setSenha(senhaCodificada);		
//		usuario .setSenha(dto.getSenha());

		return usuario;
	}
	
	public UsuarioGeralDTO toCadastro(Usuario usuario) {

		UsuarioGeralDTO dto = new UsuarioGeralDTO();
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada = encoder.encode(usuario.getSenha());
		dto.setSenha(senhaCodificada);
//		dto.setSenha(usuario.getSenha());
		
	
		return dto;
	}
	
	


}
