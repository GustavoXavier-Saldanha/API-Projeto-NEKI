package br.com.neki.entities.dtos;

import br.com.neki.entities.Usuario;

public class LoginRespostaDTO {
	private String token;
    private Usuario usuario;
    
	public LoginRespostaDTO(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Usuario getAgente() {
		return usuario;
	}
	public void setAgente(Usuario usuario) {
		this.usuario = usuario;
	}

    

}
