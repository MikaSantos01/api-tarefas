package com.mikaias.meuprojeto.service;

import com.mikaias.meuprojeto.exception.TarefaNaoEncontradaException;
import com.mikaias.meuprojeto.model.Tarefa;
import com.mikaias.meuprojeto.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public Tarefa criar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa dados) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setDescricao(dados.getDescricao());
        tarefa.setStatus(dados.getStatus());
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        repository.delete(tarefa);
    }
}