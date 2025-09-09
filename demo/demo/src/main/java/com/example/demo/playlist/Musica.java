package com.example.demo.playlist;

public record Musica(
    long id,
    String nome,
    String artista,
    String album,
    int duracao, 
    String genero
) {}