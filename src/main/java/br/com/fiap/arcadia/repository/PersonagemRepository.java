package br.com.fiap.arcadia.repository;

import br.com.fiap.arcadia.model.Personagem;
import br.com.fiap.arcadia.model.TipoClasse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    Optional<List<Personagem>> findByNome(String nome);

    Optional<List<Personagem>> findByClasse(TipoClasse classe);

}
