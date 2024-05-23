package com.example.gerenciamento.Model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimentacao_Patrimonio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMovimentacaoPatrimonio;
    @ManyToOne
    @JoinColumn(name = "solicitante", referencedColumnName = "nomeFuncionario")
    private Funcionario solicitante;
    @JoinColumn(name = "nPatrimonio", referencedColumnName = "nPatrimonio")
    private Patrimonio nPatrimonio;
    private String origem;
    private String destino;
    private String descricao;
    private String tipo;
    private String status;
    // zoned date time é para representar a data, horario e fuso horário caso
    // necessário das datas
    private ZonedDateTime dataSolicitacao;
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

    public Patrimonio getnPatrimonio() {
        return nPatrimonio;
    }

    public void setnPatrimonio(Patrimonio nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }

}
