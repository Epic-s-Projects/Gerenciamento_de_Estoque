package com.example.gerenciamento.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "salas")
@Entity
public class Sala implements Serializable {
    @Id
    private String nSala;

    @ManyToOne 
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", nullable = false)
    private Funcionario funcionario;
    
    private String categoriaSala;

    public String getnSala() {
        return nSala;
    }

    public void setnSala(String nSala) {
        this.nSala = nSala;
    }

    

    public String getCategoriaSala() {
        return categoriaSala;
    }

    public void setCategoriaSala(String categoriaSala) {
        this.categoriaSala = categoriaSala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
