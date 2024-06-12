package com.example.gerenciamento.Model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "movimentacaoEstoque")
@Entity
public class Movimentacao_Estoque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMovimentacaoEstoque;

    @ManyToOne
    @JoinColumn(name = "nomeFuncionario", referencedColumnName = "nomeFuncionario", nullable = false)
    private Funcionario solicitante;

    @JoinColumn(name = "idEstoque", referencedColumnName = "idEstoque", nullable = false)
    private Estoque idEstoque;

    
    @Column(length = 200)
    private String descricao;

    
    @Column(length = 50)
    private String tipo;

    
    @Column(length = 20)
    private String status;

    // zoned date time é para representar a data, horario e fuso horário caso
    // necessário das datas
    
    private ZonedDateTime dataSolicitacao;

    
    private ZonedDateTime dataAprovacao;
    public long getIdMovimentacaoEstoque() {
        return idMovimentacaoEstoque;
    }
    public void setIdMovimentacaoEstoque(long idMovimentacaoEstoque) {
        this.idMovimentacaoEstoque = idMovimentacaoEstoque;
    }
    public Funcionario getSolicitante() {
        return solicitante;
    }
    public void setSolicitante(Funcionario solicitante) {
        this.solicitante = solicitante;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ZonedDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(ZonedDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    public ZonedDateTime getDataAprovacao() {
        return dataAprovacao;
    }
    public void setDataAprovacao(ZonedDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    public Estoque getIdEstoque() {
        return idEstoque;
    }
    public void setIdEstoque(Estoque idEstoque) {
        this.idEstoque = idEstoque;
    }
    
}
