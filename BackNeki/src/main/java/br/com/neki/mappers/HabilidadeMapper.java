package br.com.neki.mappers;


import org.springframework.stereotype.Component;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.dtos.HabilidadeCadastroDTO;

@Component
public class HabilidadeMapper {

	
	public Habilidade toEntity(HabilidadeCadastroDTO dto) {
		Habilidade skill = new Habilidade();
		skill.setNome(dto.getNome());
		skill.setDescricao(dto.getDescricao());
		skill.setUrl(dto.getUrl());
		
		return skill;
	}
	
	public HabilidadeCadastroDTO toDTO(Habilidade habilidade) {
		
		HabilidadeCadastroDTO dto= new HabilidadeCadastroDTO();
		dto.setDescricao(habilidade.getDescricao());
		dto.setNome(habilidade.getNome());
		dto.setUrl(habilidade.getUrl());
		
		return dto;
	}
	
}
