package br.com.neki.mappers;

import org.springframework.stereotype.Component;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.entities.dtos.HabilidadeUsuarioDTO;

@Component
public class HabilidadeUsuarioMapper {

	
	public HabilidadesUsuario toEntity(Habilidade habilidade) {
		HabilidadesUsuario habilidadeUsuario = new HabilidadesUsuario();
		
		habilidadeUsuario.setHabilidades(habilidade);
		habilidadeUsuario.setNota(0);
		
		return habilidadeUsuario;
	}
	
	public HabilidadeUsuarioDTO toDto(HabilidadesUsuario habilidadeUsuario) {
		HabilidadeUsuarioDTO dto = new HabilidadeUsuarioDTO();
		
		dto.setHabilidade(habilidadeUsuario.getHabilidades());
		dto.setNota(habilidadeUsuario.getNota());
		
		return dto;
	}
	
}
