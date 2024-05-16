package com.example.gerenciamento.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Oficina implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_oficina;
    private Funcionario cpf;
    private String categoria;

    public Long getId_oficina() {
        return id_oficina;
    }
    public Funcionario getCpf() {
        return cpf;
    }
    public void setCpf(Funcionario cpf) {
        this.cpf = cpf;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
}
