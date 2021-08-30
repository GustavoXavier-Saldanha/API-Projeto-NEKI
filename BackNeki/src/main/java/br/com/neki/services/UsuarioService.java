package br.com.neki.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.HabilidadeUsuarioDTO;
import br.com.neki.entities.dtos.UsuarioGeralDTO;
import br.com.neki.exception.UsuarioException;
import br.com.neki.mappers.HabilidadeUsuarioMapper;
import br.com.neki.mappers.UsuarioMapper;
import br.com.neki.repositories.HabilidadesUsuarioRepository;
import br.com.neki.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private static final String headerPrefix = "Bearer ";
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioMapper usuarioMapper;
	
	@Autowired
	HabilidadeUsuarioMapper habilidadesUsuarioMapper;
	
	@Autowired
	HabilidadesUsuarioRepository habilidadesUsuarioRepository;
	
	@Autowired
	HabilidadesUsuarioService habilidadesUsuarioService;
	
	@Autowired
	HabilidadeService habilidadeService;
	
	public Usuario findById(Long id) throws EntityNotFoundException {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " não encontrado."));

	}
	
//	public List<UsuarioGeralDTO> getAll() {
//		return usuarioRepository.findAll().stream().map(usuarioMapper::toExibir).collect(Collectors.toList());
//	}
	
	public Usuario postUsuario(UsuarioGeralDTO dto) throws UsuarioException {

		if (dto.getNome() == null) {
			throw new UsuarioException("Nome não cadastrado.");
		}
		if (dto.getEmail() == null || dto.getSenha() == null) {
			throw new UsuarioException("E-mail não cadastrado.");
		}
		if (dto.getSenha() == null) {
			throw new UsuarioException("Senha não cadastrado.");
		}

		Usuario entity = usuarioMapper.toEntity(dto);
		return usuarioRepository.save(entity);

	}	

	public Usuario addSkill(Long idHabilidade, UsuarioGeralDTO dto) throws UsuarioException {
		if(dto.getId() == null || idHabilidade == null) {
			throw new UsuarioException("Algum dos campos não foi preenchido");
		}
		
		Usuario usuario = findById(dto.getId());
		Habilidade habilidade = habilidadeService.findById(idHabilidade);
		
		
		List<HabilidadesUsuario> habilidades = usuario.getHabilidades();
		
		if(!habilidades.isEmpty()) {
			for(HabilidadesUsuario skill : habilidades) {
				if(skill.getHabilidades() == habilidade)
					throw new UsuarioException("usuario ja contem a habilidade");
			}
		}
		HabilidadesUsuario habilidadesUsuario = habilidadesUsuarioMapper.toEntity(habilidade);
		habilidadesUsuarioRepository.save(habilidadesUsuario);
		habilidades.add(habilidadesUsuario);
		usuario.setHabilidades(habilidades);
		
		return usuarioRepository.save(usuario);
	}

	

	public HabilidadesUsuario editSkill(HabilidadeUsuarioDTO dto, Double nota ) throws UsuarioException {
		if(dto.getId() == null || nota == null) {
			throw new UsuarioException("id habilidade e/ou id usuario são nulos");
		}
		
		HabilidadesUsuario habilidade = habilidadesUsuarioService.findById(dto.getId());
		
		habilidade.setNota(nota);
		
		return habilidadesUsuarioRepository.save(habilidade);
	}
	
	
	
	public Usuario deleteSkill(UsuarioGeralDTO dto, Long id ) throws UsuarioException {
		if(dto.getId() == null || id == null) {
			throw new UsuarioException("id habilidade e/ou id usuario são nulos");
		}
		
		Usuario usuario = findById(dto.getId());
		HabilidadesUsuario habilidade = habilidadesUsuarioService.findById(id);
		
		List<HabilidadesUsuario> habilidades = usuario.getHabilidades();
		
		if(!habilidades.contains(habilidade)) {
			throw new UsuarioException("Habilidade não encontrada");
		}
		habilidades.remove(habilidade);
		usuario.setHabilidades(habilidades);
		
		return usuarioRepository.save(usuario);
	}
}
