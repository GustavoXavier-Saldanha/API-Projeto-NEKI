package br.com.neki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	HabilidadeService habilidadeService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> postUsuario(@RequestBody UsuarioGeralDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.postUsuario(dto), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
			throws UsuarioException {
		try {			
			return new ResponseEntity<Usuario>(usuarioService.findById(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/adiciona/{idHabilidade}")
	public ResponseEntity<?> addSkill(@PathVariable Long idSkill,@RequestBody UsuarioGeralDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.addSkill(idSkill, dto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping("/editar/habilidade/{nivel}")
	public ResponseEntity<?> editSkill(@PathVariable Double nota ,@RequestBody HabilidadeUsuarioDTO dto) {
		try {
			return new ResponseEntity<>(usuarioService.editSkill(dto, nota), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	

	@PostMapping("/remove/{idHabilidade}")
	public ResponseEntity<?> deleteSkill(@RequestBody UsuarioGeralDTO dto,@PathVariable Long idSkill) {
		try {
			return new ResponseEntity<>(usuarioService.deleteSkill(dto, idSkill), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
