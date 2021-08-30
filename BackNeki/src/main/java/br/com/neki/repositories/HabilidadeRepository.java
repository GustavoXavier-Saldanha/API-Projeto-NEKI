package br.com.neki.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.entities.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long>{

	Optional<Habilidade> findById(Long id);
}
