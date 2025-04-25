package br.com.fiap.arcadia.controller;

import br.com.fiap.arcadia.model.Personagem;
import br.com.fiap.arcadia.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> index() {
        return repository.findAll();
    }

    @GetMapping("{nome}")
    public ResponseEntity<List<Personagem>> getNome(@PathVariable String nome) {
        return ResponseEntity.ok(getPersonagemPorNome(nome));
    }

    @GetMapping("{classe}")
    public ResponseEntity<List<Personagem>> getClasse(@PathVariable String classe) {
        return ResponseEntity.ok(getPersonagemPorClasse(classe));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personagem create(@RequestBody Personagem personagem) {
        return repository.save(personagem);
    }

    @DeleteMapping
    public ResponseEntity<Personagem> delete(@PathVariable Long id) {
        repository.delete(getPersonagemPorId(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public Personagem update(@PathVariable Long id, @RequestBody Personagem personagem) {
        Personagem personagemExistente = getPersonagemPorId(id);

        personagemExistente.setNome(personagem.getNome());
        personagemExistente.setClasse(personagem.getClasse());
        personagemExistente.setNivel(personagem.getNivel());
        personagemExistente.setMoedas(personagem.getMoedas());

        return repository.save(personagem);
    }


    private Personagem getPersonagemPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum personagem encontrado com o id " + id)
                );
    }

    private List<Personagem> getPersonagemPorClasse(String classe) {
        return repository.findByClasse(classe)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum personagem encontrado com a classe " + classe)
                );
    }

    private List<Personagem> getPersonagemPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum personagem encontrado com o nome " + nome)
                );
    }


}
