package com.example.demo.playlist;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final long id;
    private String nome;
    private final List<Musica> musicas;

    public Playlist(long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
    
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }
}