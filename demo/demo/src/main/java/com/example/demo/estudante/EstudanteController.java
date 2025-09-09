package com.example.demo.estudante;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    // Usando ConcurrentHashMap para segurança em ambientes com múltiplos acessos (threads)
    private final Map<Long, Estudante> estudantes = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<Estudante> criarEstudante(@RequestBody Estudante estudante) {
        if (estudantes.containsKey(estudante.codigo())) {
             return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Código já existe
        }
        estudantes.put(estudante.codigo(), estudante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudante);
    }

    @GetMapping
    public Collection<Estudante> listarEstudantes() {
        return estudantes.values();
    }

    @GetMapping("/{codigo}")
    public Estudante buscarEstudantePorCodigo(@PathVariable Long codigo) {
        Estudante estudante = estudantes.get(codigo);
        if (estudante == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
        }
        return estudante;
    }
}