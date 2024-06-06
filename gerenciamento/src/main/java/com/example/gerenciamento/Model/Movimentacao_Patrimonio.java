package com.example.gerenciamento.Model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "movimentacaoPatrimonio")
@Entity
public class Movimentacao_Patrimonio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMovimentacaoPatrimonio;

    @ManyToOne
    @JoinColumn(name = "nomeFuncionario", referencedColumnName = "nomeFuncionario", nullable = false)
    private Funcionario solicitante;

    @JoinColumn(name = "idPatrimonio", referencedColumnName = "idPatrimonio", nullable = false)
    private Patrimonio idPatrimonio;

    @Nonnull
    @Column(length = 100)
    private String origem;

    @Nonnull
    @Column(length = 100)
    private String destino;

    @Nonnull
    @Column(length = 200)
    private String descricao;

    @Nonnull
    @Column(length = 50)
    private String tipo;

    @Nonnull
    @Column(length = 10)
    private String status;
    // zoned date time é para representar a data, horario e fuso horário caso
    // necessário das datas

    @Nonnull
    private ZonedDateTime dataSolicitacao;

    @Nonnull
    private ZonedDateTime dataAprovacao;

    public long getIdMovimentacaoPatrimonio() {
        return idMovimentacaoPatrimonio;
    }

    public void setIdMovimentacaoPatrimonio(long idMovimentacaoPatrimonio) {
        this.idMovimentacaoPatrimonio = idMovimentacaoPatrimonio;
    }

    public Funcionario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Funcionario solicitante) {
        this.solicitante = solicitante;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public Patrimonio getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(Patrimonio idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    

}
