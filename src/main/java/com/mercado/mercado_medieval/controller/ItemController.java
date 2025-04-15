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

    @PostMapping
    public ResponseEntity<Item> criarItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.salvarItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarItens();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItem(@PathVariable Long id) {
        Optional<Item> item = itemService.buscarPorId(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long id) {
        Optional<Item> item = itemService.buscarPorId(id);
        if (item.isPresent()) {
            itemService.excluirItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar itens por nome parcial
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Item>> buscarItemPorNome(@RequestParam String nome) {
        List<Item> itens = itemService.buscarPorNome(nome);
        return itens.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(itens);
    }

    // Buscar itens por tipo
    @GetMapping("/buscarPorTipo")
    public ResponseEntity<List<Item>> buscarItemPorTipo(@RequestParam String tipo) {
        List<Item> itens = itemService.buscarPorTipo(tipo);
        return itens.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(itens);
    }

    // Buscar itens por faixa de preço
    @GetMapping("/buscarPorPreco")
    public ResponseEntity<List<Item>> buscarItemPorPreco(@RequestParam double precoMinimo,
                                                    @RequestParam double precoMaximo) {
        List<Item> itens = itemService.buscarPorPreco(precoMinimo, precoMaximo);
        return itens.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(itens);
    }

    // Buscar itens por raridade
    @GetMapping("/buscarPorRaridade")
    public ResponseEntity<List<Item>> buscarItemPorRaridade(@RequestParam String raridade) {
        List<Item> itens = itemService.buscarPorRaridade(raridade);
        return itens.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(itens);
    }
}