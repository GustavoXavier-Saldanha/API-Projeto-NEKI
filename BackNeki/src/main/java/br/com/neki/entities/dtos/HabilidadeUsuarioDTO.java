package br.com.neki.entities.dtos;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.Usuario;

public class HabilidadeUsuarioDTO {

	private Long id;
	private Habilidade habilidade;
	private Integer nota;
	private Usuario usuario;
	
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
