package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    // Buscar personagem por nome
    Optional<Personagem> findByNome(String nome);

    // Buscar personagens por classe
    List<Personagem> findByClasse(String classe);
    Page<Personagem> findByNomeContainingIgnoreCaseAndClasseContainingIgnoreCase(String nome, String classe, Pageable pageable);
    
}