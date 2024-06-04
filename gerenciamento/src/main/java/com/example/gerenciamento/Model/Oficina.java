package com.example.gerenciamento.Model;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "oficinas")
@Entity
public class Oficina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOficina;

    @ManyToOne 
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", nullable = false)
    private Funcionario cpf;

    @Nonnull
    @Column(length = 100)
    private String nomeOficina;

    public Long getIdOficina() {
        return idOficina;
    }

    public Funcionario getCpf() {
        return cpf;
    }

    public void setCpf(Funcionario cpf) {
        this.cpf = cpf;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }



}
