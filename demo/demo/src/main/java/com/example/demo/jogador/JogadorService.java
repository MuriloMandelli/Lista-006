package com.example.demo.jogador;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class JogadorService {

    private static final List<String> NOMES = List.of("Ronaldo", "Lionel", "Cristiano", "Neymar", "Zinedine");
    private static final List<String> SOBRENOMES = List.of("Fenômeno", "Messi", "Ronaldo", "Júnior", "Zidane");
    private final Random random = new Random();

    public Jogador gerarJogador(String time, String posicao) {
        String nome = NOMES.get(random.nextInt(NOMES.size()));
        String sobrenome = SOBRENOMES.get(random.nextInt(SOBRENOMES.size()));
        int idade = random.nextInt(23) + 18; 
        return new Jogador(nome, sobrenome, idade, posicao, time);
    }
}