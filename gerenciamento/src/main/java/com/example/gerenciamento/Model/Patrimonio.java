package com.example.gerenciamento.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patrimonio implements Serializable {
    @Id
    private String nPatrimonio;
    private String nome;
    private String descricao;
    private String categoriaPatrimonio;
    private String status;
    private String ultimaManutencao;
    private Sala sala;
    private Oficina oficina;

    public String getNPatrimonio() {
        return nPatrimonio;
    }

    public void setNPatrimonio(String nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoriaPatrimonio() {
        return categoriaPatrimonio;
    }

    public void setCategoriaPatrimonio(String categoriaPatrimonio) {
        this.categoriaPatrimonio = categoriaPatrimonio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(String ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
}