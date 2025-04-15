package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    
}