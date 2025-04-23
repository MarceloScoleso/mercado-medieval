package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> criarItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.salvarItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Item>> listarItensFiltrados(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String raridade,
            @RequestParam(required = false) Double precoMinimo,
            @RequestParam(required = false) Double precoMaximo,
            Pageable pageable
    ) {
        Page<Item> itens = itemService.filtrarItens(nome, tipo, raridade, precoMinimo, precoMaximo, pageable);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItem(@PathVariable Long id) {
        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long id) {
        if (itemService.buscarPorId(id).isPresent()) {
            itemService.excluirItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}