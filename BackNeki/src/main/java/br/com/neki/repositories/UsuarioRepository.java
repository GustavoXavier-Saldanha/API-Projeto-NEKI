package br.com.neki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
