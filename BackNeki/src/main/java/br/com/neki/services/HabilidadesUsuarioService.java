package br.com.neki.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.repositories.HabilidadesUsuarioRepository;

@Service
public class HabilidadesUsuarioService {

	@Autowired
	HabilidadesUsuarioRepository habilidadesUsuarioRepository;
	

	public HabilidadesUsuario findById(Long id) throws EntityNotFoundException {

		return habilidadesUsuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " não encontrado."));
	}
}
