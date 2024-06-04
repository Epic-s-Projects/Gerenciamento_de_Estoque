package com.example.gerenciamento.Model;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "funcionarios")
@Entity
public class Funcionario implements Serializable {
    public Funcionario() {}
    @Id
    private String cpf;

    @Nonnull
    @Column(length = 200)
    private String nomeFuncionario;

    @Nonnull
    @Column(length = 100)
    private String cargo;

    @Nonnull
    @Column(length = 100)
    private String email;

    @Nonnull
    @Column(length = 30)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
