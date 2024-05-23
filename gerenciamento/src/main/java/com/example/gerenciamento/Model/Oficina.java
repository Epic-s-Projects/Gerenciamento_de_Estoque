package com.example.gerenciamento.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Oficina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOficina;
    private Funcionario cpf;
    private String categoriaOficina;

    public Long getIdOficina() {
        return idOficina;
    }

    public Funcionario getCpf() {
        return cpf;
    }

    public void setCpf(Funcionario cpf) {
        this.cpf = cpf;
    }

    public String getCategoria() {
        return categoriaOficina;
    }

    public void setCategoriaOficina(String categoriaOficina) {
        this.categoriaOficina = categoriaOficina;
    }

}
