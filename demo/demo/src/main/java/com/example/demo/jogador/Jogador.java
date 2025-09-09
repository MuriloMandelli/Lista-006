package com.example.demo.jogador;

public record Jogador(
    String nome,
    String sobrenome,
    int idade,
    String posicao,
    String time
) {}