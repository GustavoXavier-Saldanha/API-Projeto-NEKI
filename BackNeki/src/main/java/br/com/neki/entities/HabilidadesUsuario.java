package br.com.neki.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HabilidadesUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "HabilidadesRelacionadas", cascade = CascadeType.ALL)
	private List<HabilidadesRelacionadas> habilidades = new ArrayList<>();
	
	@ManyToOne
	private Usuario usuario;

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<HabilidadesRelacionadas> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<HabilidadesRelacionadas> habilidades) {
		this.habilidades = habilidades;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
