package com.mikaias.meuprojeto.repository;

import com.mikaias.meuprojeto.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}