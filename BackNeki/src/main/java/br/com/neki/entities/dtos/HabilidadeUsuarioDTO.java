package br.com.neki.entities.dtos;

import br.com.neki.entities.Habilidade;

public class HabilidadeUsuarioDTO {

	private Long id;
	private Habilidade habilidade;
	private Integer nota;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Habilidade getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}
	public Integer  getNota() {
		return nota;
	}
	public void setNota(Integer  nota) {
		this.nota = nota;
	}

	
}
