package br.com.neki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.entities.Usuario;
import br.com.neki.entities.dtos.HabilidadeUsuarioDTO;
import br.com.neki.entities.dtos.UsuarioGeralDTO;
import br.com.neki.exception.UsuarioException;
import br.com.neki.services.HabilidadeService;
import br.com.neki.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API - Operações de Usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	HabilidadeService habilidadeService;
	
	@ApiOperation(value = "Cadastro de Usuario")
	@PostMapping("/cadastrar")
	public ResponseEntity<?> postUsuario(@RequestBody UsuarioGeralDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.postUsuario(dto), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Pesquisa de usuario por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
			throws UsuarioException {
		try {			
			return new ResponseEntity<Usuario>(usuarioService.findById(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Relaciona a skill ao usuario")
	@PostMapping("/adiciona/{idSkill}")
	public ResponseEntity<?> addSkill(@PathVariable Long idSkill,@RequestBody UsuarioGeralDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.addSkill(idSkill, dto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Edita a relação de usuario com a skill")
	@PutMapping("/editar/habilidade/{idUsuario}")
	public ResponseEntity<?> editSkill(@PathVariable Long idUsuario ,@RequestBody HabilidadeUsuarioDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.editSkill(dto, idUsuario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Desrelaciona o usuario com a skill")
	@DeleteMapping("/remove/{idSkill}")
	public ResponseEntity<?> deleteSkill(@PathVariable Long idSkill) {
		try {
			return new ResponseEntity<>(usuarioService.deleteSkill(idSkill), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
