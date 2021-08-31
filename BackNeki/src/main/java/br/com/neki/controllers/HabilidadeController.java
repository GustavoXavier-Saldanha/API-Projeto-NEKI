package br.com.neki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.entities.Habilidade;
import br.com.neki.entities.dtos.HabilidadeCadastroDTO;
import br.com.neki.entities.dtos.UsuarioGeralDTO;
import br.com.neki.exception.HabilidadeException;
import br.com.neki.services.HabilidadeService;
import br.com.neki.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API - Skills ")
@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	HabilidadeService habilidadeService;
	
	@ApiOperation(value = "Cadastro de habilidades")
	@PostMapping("/cadastro")
	public ResponseEntity<?> postHabilidade(@RequestBody HabilidadeCadastroDTO dto) {
		try {
			return new ResponseEntity<>(habilidadeService.postHabilidade(dto), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Pesquisa de habilidades relacionadas ao usuario")
	@GetMapping("/usuario/{idHabilidade}")
	public ResponseEntity<?> findAllHabilidadesByUsuario(@PathVariable Long idHabilidade) {
		try {
			return new ResponseEntity<>(habilidadeService.findAllHabilidadesByUsuario(idHabilidade), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Pesquisa habilidades não relacionadas ao usuario")
	@PostMapping("/naorelacionadas")
	public ResponseEntity<?> findAllHabilidadesNaoRelacionadasByUsuario(@RequestBody UsuarioGeralDTO dto) {
		try {
			return new ResponseEntity<>(habilidadeService.findAllHabilidadesNaoRelacionadasByUsuario(dto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Pesquisa habilidade por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
			throws HabilidadeException {
		try {			
			return new ResponseEntity<Habilidade>(habilidadeService.findById(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@ApiOperation(value = "Edição de abilidade")
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody HabilidadeCadastroDTO dto) {
			habilidadeService.update(id, dto);
		return new ResponseEntity<>("Dados da habilidade atualizados com sucesso.", HttpStatus.ACCEPTED);

	}
	
}
