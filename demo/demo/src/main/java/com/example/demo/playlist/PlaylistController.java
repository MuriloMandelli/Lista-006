package com.example.demo.playlist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final Map<Long, Playlist> playlists = new ConcurrentHashMap<>();
    private final AtomicLong playlistIdCounter = new AtomicLong();
    private final AtomicLong musicaIdCounter = new AtomicLong();

    @PostMapping
    public ResponseEntity<Long> criarPlaylist(@RequestBody Map<String, String> payload) {
        String nome = payload.get("nome");
        if (nome == null || nome.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        long newId = playlistIdCounter.incrementAndGet();
        Playlist novaPlaylist = new Playlist(newId, nome);
        playlists.put(newId, novaPlaylist);
        return ResponseEntity.status(HttpStatus.CREATED).body(newId);
    }

    @GetMapping
    public Collection<Playlist> listarPlaylists() {
        return playlists.values();
    }

    @PostMapping("/{id}/musicas")
    public ResponseEntity<String> adicionarMusica(
        @PathVariable Long id, 
        @RequestBody Musica musicaRequest
    ) {
        Playlist playlist = playlists.get(id);
        if (playlist == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }

        long novaMusicaId = musicaIdCounter.incrementAndGet();
        Musica novaMusica = new Musica(
            novaMusicaId,
            musicaRequest.nome(),
            musicaRequest.artista(),
            musicaRequest.album(),
            musicaRequest.duracao(),
            musicaRequest.genero()
        );
        
        playlist.adicionarMusica(novaMusica);
        return ResponseEntity.ok("Música adicionada com sucesso à playlist " + id);
    }

    @GetMapping("/{id}/musicas")
    public List<Musica> listarMusicasDaPlaylist(@PathVariable Long id) {
        Playlist playlist = playlists.get(id);
        if (playlist == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }
        return playlist.getMusicas();
    }
}