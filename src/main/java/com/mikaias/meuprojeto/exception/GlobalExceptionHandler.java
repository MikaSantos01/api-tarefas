package com.mikaias.meuprojeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrada(TarefaNaoEncontradaException ex) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("status", 404);
        corpo.put("erro", ex.getMessage());
        corpo.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erros.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("status", 400);
        corpo.put("erros", erros);
        corpo.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(corpo);
    }
}