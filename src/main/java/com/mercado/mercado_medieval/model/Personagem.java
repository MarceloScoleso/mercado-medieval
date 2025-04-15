package com.mercado.mercado_medieval.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A classe é obrigatória.")
    @Pattern(regexp = "guerreiro|mago|arqueiro", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Classe deve ser guerreiro, mago ou arqueiro.")
    private String classe;

    @Min(value = 1, message = "O nível mínimo é 1.")
    @Max(value = 99, message = "O nível máximo é 99.")
    private int nivel;

    @Min(value = 0, message = "As moedas não podem ser negativas.")
    private double moedas;
}