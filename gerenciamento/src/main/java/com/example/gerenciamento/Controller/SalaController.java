package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Model.Sala;
import com.example.gerenciamento.Repository.SalaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaController {
    @Autowired
    SalaRepository sr;

    @PostMapping("/registra-sala")
    public String registraSala(Sala sa) {
        sr.save(sa);

        return "";
    }

    @PostMapping("/deletar-sala")
    public String deletaSala(@RequestParam String nSala) {
        Sala sala = sr.findByNSala(nSala);
        sr.delete(sala);
        return "";
    }

    @PostMapping("/atualizar-sala")
    public String atualizaSala(@RequestParam String nSala,
            @RequestParam String categoriaSala,
            @RequestParam Funcionario cpf) {
        Sala sala = sr.findByNSala(nSala);
        sala.setCategoriaSala(categoriaSala);
        sala.setCpf(cpf);
        return "";
    }

}
