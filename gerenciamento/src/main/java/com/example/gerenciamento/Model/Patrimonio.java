package com.example.gerenciamento.Model;

import java.io.Serializable;

import jakarta.persistence.Id;

public class Patrimonio implements Serializable{
    @Id
    private String n_patrimonio;
    private String nome;
    private String descricao;
    private String categoria;
    private String status;
    private String ultima_manutencao;
    private Sala sala;
    private Oficina oficina;

    public String getN_patrimonio() {
        return n_patrimonio;
    }
    public void setN_patrimonio(String n_patrimonio) {
        this.n_patrimonio = n_patrimonio;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUltima_manutencao() {
        return ultima_manutencao;
    }
    public void setUltima_manutencao(String ultima_manutencao) {
        this.ultima_manutencao = ultima_manutencao;
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