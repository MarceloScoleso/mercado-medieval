package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criarPersonagem(@Valid @RequestBody Personagem personagem) {
        Personagem saved = personagemService.salvarPersonagem(personagem);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Personagem>> listarPersonagensFiltrados(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String classe,
            Pageable pageable
    ) {
        Page<Personagem> personagens = personagemService.filtrarPersonagens(nome, classe, pageable);
        return ResponseEntity.ok(personagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (personagemService.buscarPorId(id).isPresent()) {
            personagemService.excluirPersonagem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}