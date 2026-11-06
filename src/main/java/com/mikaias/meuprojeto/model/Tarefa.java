package com.mikaias.meuprojeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status = StatusTarefa.PENDENTE;

    private LocalDateTime dataCriacao = LocalDateTime.now();

}