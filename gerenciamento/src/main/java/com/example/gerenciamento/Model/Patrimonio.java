package com.example.gerenciamento.Model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "patrimonios")
@Entity
public class Patrimonio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatrimonio;

    
    @Column(length = 200)
    private String nPatrimonio;

    
    @Column(length = 200)
    private String nome;

    
    @Column(length = 150)
    private String descricao;

    
    @Column(length = 100)
    private String categoriaPatrimonio;

    
    @Column(length = 10)
    private String status;

    
    @Column(length = 50)
    private String ultimaManutencao;

    @OneToOne
    @JoinColumn(name = "nSala", referencedColumnName = "nSala", nullable = false)
    private Sala sala;

    @OneToOne
    @JoinColumn(name = "idOficina", referencedColumnName = "idOficina", nullable = false)
    private Oficina oficina;

    public Patrimonio() {}

    

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



    public Long getIdPatrimonio() {
        return idPatrimonio;
    }



    public void setIdPatrimonio(Long idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }



    public String getnPatrimonio() {
        return nPatrimonio;
    }



    public void setnPatrimonio(String nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }



    
}