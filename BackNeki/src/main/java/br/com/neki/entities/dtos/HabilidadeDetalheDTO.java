package br.com.neki.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HabilidadeDetalheDTO {

	@NotNull
	@NotBlank
	private String nome;
	
	@NotNull
	@NotBlank
	private String descricao;
	
	@NotNull
	@NotBlank
	private String url;

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
