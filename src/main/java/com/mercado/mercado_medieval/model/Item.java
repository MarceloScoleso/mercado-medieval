package com.mercado.mercado_medieval.model;

import com.mercado.mercado_medieval.enums.ItemRaridade;
import com.mercado.mercado_medieval.enums.ItemTipo;
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

@NotNull(message = "O tipo é obrigatório.")
@Enumerated(EnumType.STRING)
private ItemTipo tipo;

@NotNull(message = "A raridade é obrigatória.")
@Enumerated(EnumType.STRING)
private ItemRaridade raridade;

@Min(value = 0, message = "O preço não pode ser negativo.")
private double preco;

@ManyToOne
@JoinColumn(name = "personagem_id", referencedColumnName = "id")
private Personagem dono;
}