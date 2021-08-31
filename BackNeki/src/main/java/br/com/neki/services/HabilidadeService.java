package br.com.neki.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.HabilidadeCadastroDTO;
import br.com.neki.entities.dtos.UsuarioGeralDTO;
import br.com.neki.exception.HabilidadeException;
import br.com.neki.mappers.HabilidadeMapper;
import br.com.neki.repositories.HabilidadeRepository;
import br.com.neki.repositories.HabilidadesUsuarioRepository;

@Service
public class HabilidadeService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	HabilidadeRepository habilidadeRepository;
	
	@Autowired
	HabilidadeMapper habilidadeMapper;
	
	@Autowired
	HabilidadesUsuarioRepository habilidadesUsuarioRepository;
	
	
	public Habilidade findById(Long id) throws EntityNotFoundException {
		return habilidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " não encontrado."));
	}
	
	
	public List<HabilidadesUsuario> findAllHabilidadesByUsuario(Long idUsuario) throws HabilidadeException {
		if (idUsuario == null) {
			throw new HabilidadeException("Usuario nulo");
		}
		Usuario usuario = usuarioService.findById(idUsuario);
		List<HabilidadesUsuario> habilidades = (usuario.getHabilidades());
		
		return habilidades;
	}
	
	
	public List<Habilidade> findAllHabilidadesNaoRelacionadasByUsuario(UsuarioGeralDTO dto) throws HabilidadeException {
		if (dto.getId() == null) {
			throw new HabilidadeException("Usuario não existe");
		}
		List<Habilidade> listaHabilidades = habilidadeRepository.findAll();
		Usuario usuario = usuarioService.findById(dto.getId());
		List<HabilidadesUsuario> habilidadesUsuario = (usuario.getHabilidades());
		
		for(HabilidadesUsuario skill : habilidadesUsuario) {
			listaHabilidades.remove(skill.getHabilidades());
			
		}
		
		return listaHabilidades;
	}
	
	
	public Habilidade postHabilidade(HabilidadeCadastroDTO dto) throws HabilidadeException {

		if (dto.getNome() == null ) {
			throw new HabilidadeException("Nome não cadastrad.o");
		}
		if (dto.getDescricao() == null ) {
			throw new HabilidadeException("Descrição não cadastrada.");
		}
		if (dto.getUrl() == null) {
			throw new HabilidadeException("Imagem não cadastrada.");
		}

		Habilidade entity = habilidadeMapper.toEntity(dto);
		return habilidadeRepository.save(entity);

	}
	
	public HabilidadeCadastroDTO update(Long id, HabilidadeCadastroDTO dto) throws EntityNotFoundException {
		Habilidade habilidade = this.findById(id);
		habilidade.setNome(dto.getNome());
		habilidade.setDescricao(dto.getDescricao());
		habilidade.setUrl(dto.getUrl());
		
		return habilidadeMapper.toDTO(habilidadeRepository.save(habilidade));

	}
	
	
}
