package com.example.demo.viagem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendar")
public class ViagemController {

    @GetMapping
    public String recomendarViagem(@RequestParam String clima, @RequestParam String estilo) {
        if ("calor".equalsIgnoreCase(clima) && "natureza".equalsIgnoreCase(estilo)) {
            return "Rio de Janeiro";
        } else if ("calor".equalsIgnoreCase(clima) && "urbano".equalsIgnoreCase(estilo)) {
            return "São Paulo";
        } else if ("frio".equalsIgnoreCase(clima) && "natureza".equalsIgnoreCase(estilo)) {
            return "Gramado";
        } else if ("frio".equalsIgnoreCase(clima) && "urbano".equalsIgnoreCase(estilo)) {
            return "Curitiba";
        } else {
            return "Destino não encontrado para a combinação fornecida.";
        }
    }
}