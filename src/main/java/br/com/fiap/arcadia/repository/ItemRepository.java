package br.com.fiap.arcadia.repository;

import br.com.fiap.arcadia.model.Item;
import br.com.fiap.arcadia.model.TipoItem;
import br.com.fiap.arcadia.model.TipoRaridade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<List<Item>> findByRaridade(TipoRaridade raridade);
    Optional<List<Item>> findByTipo(TipoItem tipo);
    Optional<List<Item>> findByNomeContainingIgnoreCase(String nome);
    Optional<Item> findFirstByOrderByPrecoAsc();
    Optional<Item> findFirstByOrderByPrecoDesc();
}
