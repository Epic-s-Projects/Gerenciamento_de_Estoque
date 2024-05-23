package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Repository.FuncionarioRepository;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class FuncionarioController {
    @Autowired
    FuncionarioRepository fr;

    @PostMapping("/registraFuncionario")
    public String registraFuncionario(Funcionario fn) {
        fr.save(fn);
        return "";
    }
    
    @PostMapping("/deletar-funcionario")
    public String deletaFuncionario(@RequestParam String cpf) {
        Funcionario funcionario = fr.findByCpf(cpf);
        fr.delete(funcionario);

        return "";
    }
    
}
