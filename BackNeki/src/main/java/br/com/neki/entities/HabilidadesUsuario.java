package br.com.neki.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class HabilidadesUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Habilidade habilidades;
	
	@NotNull
	private Integer  nota;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Habilidade getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(Habilidade habilidades) {
		this.habilidades = habilidades;
	}
	public Integer  getNota() {
		return nota;
	}
	public void setNota(Integer  nota) {
		this.nota = nota;
	}
	
}
