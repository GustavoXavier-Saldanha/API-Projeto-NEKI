package br.com.neki.mappers;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.entities.dtos.HabilidadeUsuarioDTO;

public class HabilidadeUsuarioMapper {

	
	public HabilidadesUsuario toEntity(Habilidade habilidade) {
		HabilidadesUsuario habilidadeUsuario = new HabilidadesUsuario();
		
		habilidadeUsuario.setHabilidades(habilidade);
		habilidadeUsuario.setNota(0.0);
		
		return habilidadeUsuario;
	}
	
	public HabilidadeUsuarioDTO toDto(HabilidadesUsuario habilidadeUsuario) {
		HabilidadeUsuarioDTO dto = new HabilidadeUsuarioDTO();
		
		dto.setHabilidade(habilidadeUsuario.getHabilidades());
		dto.setNota(habilidadeUsuario.getNota());
		
		return dto;
	}
	
}
