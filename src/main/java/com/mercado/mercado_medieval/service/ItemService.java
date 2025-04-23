package com.mercado.mercado_medieval.service;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Buscar itens com múltiplos filtros (opcionais) e paginação
    public Page<Item> filtrarItens(String nome, String tipo, String raridade, Double precoMin, Double precoMax, Pageable pageable) {
        List<Item> itens = itemRepository.findAll();

        List<Item> filtrados = itens.stream()
    .filter(item -> nome == null || item.getNome().toLowerCase().contains(nome.toLowerCase()))
    .filter(item -> tipo == null || item.getTipo().name().equalsIgnoreCase(tipo))
    .filter(item -> raridade == null || item.getRaridade().name().equalsIgnoreCase(raridade))
    .filter(item -> precoMin == null || item.getPreco() >= precoMin)
    .filter(item -> precoMax == null || item.getPreco() <= precoMax)
    .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtrados.size());

        List<Item> pagina = filtrados.subList(start, end);
        return new PageImpl<>(pagina, pageable, filtrados.size());
    }
}