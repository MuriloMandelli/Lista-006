package com.example.demo.estudante;

public record Estudante(
    String nome,
    long codigo,
    String curso,
    String email,
    String telefone
) {}