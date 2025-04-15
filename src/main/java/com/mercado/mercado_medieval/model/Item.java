package com.mercado.mercado_medieval.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo é obrigatório.")
    @Pattern(regexp = "arma|armadura|poção|acessório", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Tipo deve ser arma, armadura, poção ou acessório.")
    private String tipo;

    @NotBlank(message = "A raridade é obrigatória.")
    @Pattern(regexp = "comum|raro|épico|lendário", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Raridade deve ser comum, raro, épico ou lendário.")
    private String raridade;

    @Min(value = 0, message = "O preço não pode ser negativo.")
    private double preco;

    @ManyToOne
    @JoinColumn(name = "personagem_id", referencedColumnName = "id")
    private Personagem dono;
}