package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    // Buscar personagem por nome
    Optional<Personagem> findByNome(String nome);

    // Buscar personagens por classe
    List<Personagem> findByClasse(String classe);
}