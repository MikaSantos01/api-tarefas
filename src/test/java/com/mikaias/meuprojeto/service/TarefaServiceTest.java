package com.mikaias.meuprojeto.service;

import com.mikaias.meuprojeto.exception.TarefaNaoEncontradaException;
import com.mikaias.meuprojeto.model.StatusTarefa;
import com.mikaias.meuprojeto.model.Tarefa;
import com.mikaias.meuprojeto.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    @Test
    void deveBuscarTarefaPorId() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Estudar");
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa resultado = service.buscarPorId(1L);

        assertEquals("Estudar", resultado.getTitulo());
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveCriarTarefaComStatusPendente() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Nova tarefa");
        when(repository.save(tarefa)).thenReturn(tarefa);

        Tarefa resultado = service.criar(tarefa);

        assertEquals(StatusTarefa.PENDENTE, resultado.getStatus());
        verify(repository).save(tarefa);
    }

    @Test
    void deveAtualizarTarefaExistente() {
        Tarefa existente = new Tarefa();
        existente.setTitulo("Antigo");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(existente)).thenReturn(existente);

        Tarefa dados = new Tarefa();
        dados.setTitulo("Novo");
        dados.setStatus(StatusTarefa.CONCLUIDA);

        Tarefa resultado = service.atualizar(1L, dados);

        assertEquals("Novo", resultado.getTitulo());
        assertEquals(StatusTarefa.CONCLUIDA, resultado.getStatus());
    }

    @Test
    void naoDeveDeletarTarefaInexistente() {
        when(repository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> service.deletar(5L));
        verify(repository, never()).delete(any());
    }
}