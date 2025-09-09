package com.example.demo.jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping("/{time}/{posicao}")
    public Jogador getJogadorAleatorio(@PathVariable String time, @PathVariable String posicao) {
        String timeFormatado = time.substring(0, 1).toUpperCase() + time.substring(1).toLowerCase();
        String posicaoFormatada = posicao.substring(0, 1).toUpperCase() + posicao.substring(1).toLowerCase();
        
        return jogadorService.gerarJogador(timeFormatado, posicaoFormatada);
    }
}