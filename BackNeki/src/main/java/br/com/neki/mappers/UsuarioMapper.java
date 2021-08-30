package br.com.neki.mappers;

import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.UsuarioCadastroDTO;

public class UsuarioMapper {

	public Usuario toEntity(UsuarioCadastroDTO dto) {
		Usuario usuario = new Usuario();
		usuario .setNome(dto.getNome());
		usuario .setEmail(dto.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada  = encoder.encode(dto.getSenha());
		usuario .setSenha(senhaCodificada);		
		

		return usuario;
	}

}
