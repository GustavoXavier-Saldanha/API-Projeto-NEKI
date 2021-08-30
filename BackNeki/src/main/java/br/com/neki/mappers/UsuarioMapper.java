package br.com.neki.mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.UsuarioGeralDTO;

public class UsuarioMapper {

	public Usuario toEntity(UsuarioGeralDTO dto) {
		Usuario usuario = new Usuario();
		usuario .setNome(dto.getNome());
		usuario .setEmail(dto.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada  = encoder.encode(dto.getSenha());
		usuario .setSenha(senhaCodificada);		
		

		return usuario;
	}
	
	public UsuarioGeralDTO toCadastro(Usuario usuario) {

		UsuarioGeralDTO dto = new UsuarioGeralDTO();
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada = encoder.encode(usuario.getSenha());
		dto.setSenha(senhaCodificada);

	
		return dto;
	}
	
	


}
