package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Buscar itens por nome parcial
    List<Item> findByNomeContainingIgnoreCase(String nome);

    // Buscar itens por tipo
    List<Item> findByTipo(String tipo);

    // Buscar itens por preço mínimo e máximo
    List<Item> findByPrecoBetween(double precoMinimo, double precoMaximo);

    // Buscar itens por raridade
    List<Item> findByRaridade(String raridade);
}