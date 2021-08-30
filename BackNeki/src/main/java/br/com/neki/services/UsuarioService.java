package br.com.neki.services;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.HabilidadesUsuario;
import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.HabilidadeUsuarioDTO;
import br.com.neki.entities.dtos.LoginRespostaDTO;
import br.com.neki.entities.dtos.UsuarioGeralDTO;
import br.com.neki.exception.EmailOrPasswordNotValidException;
import br.com.neki.exception.UsuarioException;
import br.com.neki.mappers.HabilidadeUsuarioMapper;
import br.com.neki.mappers.UsuarioMapper;
import br.com.neki.repositories.HabilidadesUsuarioRepository;
import br.com.neki.repositories.UsuarioRepository;
import br.com.neki.security.JWTService;

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
	
	@Autowired
	JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
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

	public Usuario addSkill(Long idSkill, UsuarioGeralDTO dto) throws UsuarioException {
		if(dto.getId() == null || idSkill == null) {
			throw new UsuarioException("Algum dos campos não foi preenchido");
		}
		
		Usuario usuario = findById(dto.getId());
		Habilidade habilidade = habilidadeService.findById(idSkill);
		
		
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

	

	public HabilidadesUsuario editSkill(HabilidadeUsuarioDTO dto, Long idUsuario ) throws UsuarioException {
		if(dto.getId() == null || idUsuario  == null) {
			throw new UsuarioException("É necessário preencher todos os campos");
		}
		
		HabilidadesUsuario habilidade = habilidadesUsuarioService.findById(dto.getId());
		
		habilidade.setNota(dto.getNota());
		
		return habilidadesUsuarioRepository.save(habilidade);
	}
	
	
	
	public Usuario deleteSkill(UsuarioGeralDTO dto, Long idSkill ) throws UsuarioException {
		if(dto.getId() == null || idSkill == null) {
			throw new UsuarioException("id habilidade e/ou id usuario são nulos");
		}
		
		Usuario usuario = findById(dto.getId());
		HabilidadesUsuario habilidade = habilidadesUsuarioService.findById(idSkill);
		
		List<HabilidadesUsuario> habilidades = usuario.getHabilidades();
		
		if(!habilidades.contains(habilidade)) {
			throw new UsuarioException("Habilidade não encontrada");
		}
		habilidades.remove(habilidade);
		usuario.setHabilidades(habilidades);
		
		return usuarioRepository.save(usuario);
	}
	
	
	
	public LoginRespostaDTO logar(String email, String senha) throws EmailOrPasswordNotValidException {
		
		Authentication autenticacao = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));

		SecurityContextHolder.getContext().setAuthentication(autenticacao);

		String token = headerPrefix + jwtService.gerarToken(autenticacao);

		var usuario = usuarioRepository.findByEmail(email);
		
		return new LoginRespostaDTO(token, usuario.get());
	}
}
