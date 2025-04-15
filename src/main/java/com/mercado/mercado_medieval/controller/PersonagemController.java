package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criarPersonagem(@Valid @RequestBody Personagem personagem) {
        Personagem savedPersonagem = personagemService.salvarPersonagem(personagem);
        return new ResponseEntity<>(savedPersonagem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Personagem>> listarPersonagens() {
        List<Personagem> personagens = personagemService.listarPersonagens();
        return new ResponseEntity<>(personagens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPersonagem(@PathVariable Long id) {
        Optional<Personagem> personagem = personagemService.buscarPorId(id);
        return personagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPersonagem(@PathVariable Long id) {
        Optional<Personagem> personagem = personagemService.buscarPorId(id);
        if (personagem.isPresent()) {
            personagemService.excluirPersonagem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar personagem por nome
    @GetMapping("/buscarPorNome")
    public ResponseEntity<Personagem> buscarPersonagemPorNome(@RequestParam String nome) {
        Optional<Personagem> personagem = personagemService.buscarPorNome(nome);
        return personagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar personagens por classe
    @GetMapping("/buscarPorClasse")
    public ResponseEntity<List<Personagem>> buscarPersonagemPorClasse(@RequestParam String classe) {
        List<Personagem> personagens = personagemService.buscarPorClasse(classe);
        return personagens.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(personagens);
    }
}