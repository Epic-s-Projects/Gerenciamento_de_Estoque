package com.example.gerenciamento.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public String index() {
        return "formSala";
    }
    @GetMapping("/formCadFuncionarios")
    public String formCadFuncionarios() {
        return "formCadFuncionarios";
    }
    @GetMapping("/formEstoque")
    public String formEstoque() {
        return "formEstoque";
    }
    @GetMapping("/formOficina")
    public String formOficina() {
        return "formOficina";
    }
    @GetMapping("/formPatrimonio")
    public String formPatrimonio() {
        return "formPatrimonio";
    }
}