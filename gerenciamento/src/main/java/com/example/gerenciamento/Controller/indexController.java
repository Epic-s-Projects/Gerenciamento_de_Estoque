package com.example.gerenciamento.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("/")
    public String acessoHomePage() {
        return "index";
    }

    // @GetMapping("/patrimonio")
    // public String acessoPatrimonio() {
    // return "patrimonio";
    // }
    @GetMapping("/login")
    public String acessoLoginFuncionario() {
        return "login/login-funcionario";
    }

    @GetMapping("/cadastro-funcionario")
    public String acessoCadastroFuncionario() {
        return "cadastro/cadastro-funcionario";
    }

    @GetMapping("/manutencoes")
    public String acessoManutencoes() {
        return "manutencoes/manutencoes";
    }

    @GetMapping("/cadastro-estoque")
    public String acessoCadEstoque() {
        return "crud/estoque/cadastro-estoque";
    }

    @GetMapping("/cadastro-patrimonio")
    public String acessoCadPatrimonio() {
        return "crud/patrimonio/cadastro-patrimonio";
    }
    
    @GetMapping("/cadastro-oficina")
    public String acessoCadOficina() {
        return "crud/oficina/cadastro-oficina";
    }

    @GetMapping("/cadastro-sala")
    public String acessoCadSala() {
        return "crud/sala/cadastro-sala";
    }
}
