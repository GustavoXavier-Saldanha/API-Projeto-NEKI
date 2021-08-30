package br.com.neki.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HabilidadesRelacionadas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Habilidade habilidade;

	@JsonIgnore
	@ManyToOne
	private HabilidadesUsuario habilidadeUsuario;

	
	
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
	public HabilidadesUsuario getHabilidadeUsuario() {
		return habilidadeUsuario;
	}
	public void setHabilidadeUsuario(HabilidadesUsuario habilidadeUsuario) {
		this.habilidadeUsuario = habilidadeUsuario;
	}
	
	
}
