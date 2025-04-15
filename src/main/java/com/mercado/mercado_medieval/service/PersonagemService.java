package com.mercado.mercado_medieval.service;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    // Criar ou atualizar personagem
    public Personagem salvarPersonagem(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    // Buscar todos os personagens
    public List<Personagem> listarPersonagens() {
        return personagemRepository.findAll();
    }

    // Buscar personagem por id
    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    // Excluir personagem por id
    public void excluirPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    // Buscar personagem por nome
    public Optional<Personagem> buscarPorNome(String nome) {
        return personagemRepository.findByNome(nome);
    }

    // Buscar personagens por classe
    public List<Personagem> buscarPorClasse(String classe) {
        return personagemRepository.findByClasse(classe);
    }
}
