package br.com.fiap.arcadia.config;

import br.com.fiap.arcadia.model.Item;
import br.com.fiap.arcadia.model.Personagem;
import br.com.fiap.arcadia.model.TipoClasse;
import br.com.fiap.arcadia.model.TipoItem;
import br.com.fiap.arcadia.model.TipoRaridade;
import br.com.fiap.arcadia.repository.ItemRepository;
import br.com.fiap.arcadia.repository.PersonagemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    private Random random = new Random();

    @PostConstruct
    public void init() {

        List<String> nomesPersonagens = new ArrayList<>(List.of(
                "Arthas", "Sylvanas", "Legolas", "Merlin", "Gandalf",
                "Aragorn", "Eowyn", "Frodo", "Elrond", "Galadriel",
                "Thranduil", "Boromir", "Gimli", "Tauriel", "Radagast",
                "Dumbledore", "Snape", "Hermione", "Draco", "Severus"
        ));

        Collections.shuffle(nomesPersonagens);

        var personagens = new ArrayList<Personagem>();

        for (int i = 0; i < 20; i++) {
            personagens.add(
                    Personagem.builder()
                            .nome(nomesPersonagens.get(i))
                            .classe(TipoClasse.values()[random.nextInt(TipoClasse.values().length)])
                            .nivel(random.nextInt(99) + 1)
                            .moedas(random.nextInt(5000) + 100)
                            .build()
            );
        }

        personagemRepository.saveAll(personagens);

        List<String> nomesItens = List.of(
                "Espada de Fogo", "Armadura de Gelo", "Poção de Cura", "Anel da Invisibilidade",
                "Espada Longa", "Armadura de Ferro", "Poção de Mana", "Colar do Poder",
                "Espada Curta", "Armadura de Ouro", "Poção de Vida", "Bracelete do Vento",
                "Espada Flamejante", "Armadura Sombria", "Poção Mágica", "Amuleto da Sorte",
                "Espada de Prata", "Armadura de Dragão", "Poção da Força", "Tiara da Sabedoria"
        );

        var tiposItens = List.of(TipoItem.ARMA, TipoItem.ARMADURA, TipoItem.POCAO, TipoItem.ACESSORIO);
        var raridades = List.of(TipoRaridade.COMUM, TipoRaridade.RARO, TipoRaridade.EPICO, TipoRaridade.LENDARIO);

        var itens = new ArrayList<Item>();

        for (int i = 0; i < nomesItens.size(); i++) {
            itens.add(
                    Item.builder()
                            .nome(nomesItens.get(i))
                            .tipo(tiposItens.get(random.nextInt(tiposItens.size())))
                            .raridade(raridades.get(random.nextInt(raridades.size())))
                            .preco(random.nextInt(1000) + 50)
                            .dono(personagens.get(random.nextInt(personagens.size())))
                            .build()
            );
        }

        itemRepository.saveAll(itens);
    }
}