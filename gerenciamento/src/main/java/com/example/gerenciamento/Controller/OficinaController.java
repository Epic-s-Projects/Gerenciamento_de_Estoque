package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Model.Oficina;
import com.example.gerenciamento.Repository.OficinaRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OficinaController {
    @Autowired
    OficinaRepository or;

    @PostMapping("/registra-oficina")
    public String registraOficina(Oficina of) {
        or.save(of);
        return "";
    }

    @PostMapping("/deletar-oficina")
    public String deletaOficina(@RequestParam String idOficina) {
        Oficina oficina = or.findByIdOficina(idOficina);
        or.delete(oficina);
        return "";
    }

    @PostMapping("/atualizar-oficina")
    public String atualizaOficina(@RequestParam String idOficina,
            @RequestParam String nomeOficina,
            @RequestParam Funcionario cpf) {
        Oficina oficina = or.findByIdOficina(idOficina);
        oficina.setNomeOficina(nomeOficina);
        oficina.setCpf(cpf);
        return "";
    }
}
