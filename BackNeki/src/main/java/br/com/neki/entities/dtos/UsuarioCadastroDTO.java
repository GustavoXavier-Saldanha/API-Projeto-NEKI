package br.com.neki.entities.dtos;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioCadastroDTO {
	
	@NotEmpty
	@Column(length = 80)
	@Size(min = 5, max = 80)
	private String nome;

	@NotEmpty
	@Email
	@Column(unique = true, length = 64)
	private String email;

	@NotEmpty
	@Column(length = 255)
	@Size(min = 8, max = 255)
	private String senha;

	
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
	
	
	
}
