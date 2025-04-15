package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}