package br.com.neki.entities.dtos;

import java.util.List;

import br.com.neki.entities.HabilidadesUsuario;

public class UsuarioGeralDTO {
	
	private Long Id;
	
	private String nome;

	private String email;

	private String senha;

	private List<HabilidadesUsuario> habilidades;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<HabilidadesUsuario> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<HabilidadesUsuario> habilidades) {
		this.habilidades = habilidades;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	
	
}
