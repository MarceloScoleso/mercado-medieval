package com.mercado.mercado_medieval.service;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    public void excluirItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Buscar itens por nome (parcial e case-insensitive)
    public List<Item> buscarPorNome(String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Buscar itens por tipo
    public List<Item> buscarPorTipo(String tipo) {
        return itemRepository.findByTipo(tipo);
    }

    // Buscar itens por faixa de preço
    public List<Item> buscarPorPreco(double precoMinimo, double precoMaximo) {
        return itemRepository.findByPrecoBetween(precoMinimo, precoMaximo);
    }

    // Buscar itens por raridade
    public List<Item> buscarPorRaridade(String raridade) {
        return itemRepository.findByRaridade(raridade);
    }
}