package br.com.fiap.arcadia.controller;

import br.com.fiap.arcadia.model.Item;
import br.com.fiap.arcadia.model.TipoItem;
import br.com.fiap.arcadia.model.TipoRaridade;
import br.com.fiap.arcadia.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> index() {
        return repository.findAll();
    }

    @GetMapping("/raridade/{raridade}")
    public List<Item> getRaridade(@PathVariable String raridade) {
        try {
            TipoRaridade raridadeEnum = TipoRaridade.valueOf(raridade.toUpperCase());
            return repository.findByRaridade(raridadeEnum)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado com a raridade: " + raridade));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Raridade inválida: " + raridade);
        }
    }

    @GetMapping("/tipo/{tipo}")
    public List<Item> getTipo(@PathVariable String tipo) {
        try {
            TipoItem tipoEnum = TipoItem.valueOf(tipo.toUpperCase());
            return repository.findByTipo(tipoEnum)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado com o tipo: " + tipo));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo inválido: " + tipo);
        }
    }

    @GetMapping("/nome")
    public List<Item> getPorNome(@RequestParam String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado com o nome contendo: " + nome));
    }

    @GetMapping("/minPreco")
    public Item getMinPreco() {
        return repository.findFirstByOrderByPrecoAsc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado"));
    }

    @GetMapping("/maxPreco")
    public Item getMaxPreco() {
        return repository.findFirstByOrderByPrecoDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado"));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Item> create(@RequestBody Item item) {
        repository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        repository.delete(getItem(id));
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        Item itemExistente = getItem(id);

        itemExistente.setNome(item.getNome());
        itemExistente.setDono(item.getDono());
        itemExistente.setTipo(item.getTipo());
        itemExistente.setPreco(item.getPreco());
        itemExistente.setRaridade(item.getRaridade());

        return repository.save(itemExistente);
    }

    private Item getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com id " + id + " não encontrado"));
    }
}
