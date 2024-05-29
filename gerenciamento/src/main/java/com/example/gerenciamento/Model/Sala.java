package com.example.gerenciamento.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "salas")
@Entity
public class Sala implements Serializable {
    @Id
    private String nSala;
    private Funcionario cpf;
    private String categoriaSala;

    public String getnSala() {
        return nSala;
    }

    public void setnSala(String nSala) {
        this.nSala = nSala;
    }

    public Funcionario getCpf() {
        return cpf;
    }

    public void setCpf(Funcionario cpf) {
        this.cpf = cpf;
    }

    public String getCategoriaSala() {
        return categoriaSala;
    }

    public void setCategoriaSala(String categoriaSala) {
        this.categoriaSala = categoriaSala;
    }

}
