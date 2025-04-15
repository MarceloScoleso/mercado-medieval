package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Criar ou atualizar item
    @PostMapping
    public ResponseEntity<Item> criarItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.salvarItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // Listar todos os itens
    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarItens();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    // Buscar item por id
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItem(@PathVariable Long id) {
        Optional<Item> item = itemService.buscarPorId(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir item por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long id) {
        Optional<Item> item = itemService.buscarPorId(id);
        if (item.isPresent()) {
            itemService.excluirItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}