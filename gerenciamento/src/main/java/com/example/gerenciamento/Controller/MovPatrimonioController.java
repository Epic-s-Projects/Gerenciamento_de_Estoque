package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Movimentacao_Patrimonio;
import com.example.gerenciamento.Services.MovPatrimonioService;

import java.util.Optional;

@RestController
@RequestMapping("/api/mov_patrimonio")
public class MovPatrimonioController {

    @Autowired
    private MovPatrimonioService movPatrimonioService;

    @GetMapping
    public Iterable<Movimentacao_Patrimonio> getAllMovimentacoes() {
        return movPatrimonioService.findAll();
    }

    @GetMapping("/{idMovimentacaoPatrimonio}")
    public ResponseEntity<Movimentacao_Patrimonio> getMovimentacaoById(@PathVariable Long idMovimentacaoPatrimonio) {
        Optional<Movimentacao_Patrimonio> movimentacao = movPatrimonioService.findById(idMovimentacaoPatrimonio);
        if (movimentacao.isPresent()) {
            return ResponseEntity.ok(movimentacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Movimentacao_Patrimonio createMovimentacao(@RequestBody Movimentacao_Patrimonio movimentacaoPatrimonio) {
        return movPatrimonioService.save(movimentacaoPatrimonio);
    }

    @PutMapping("/{idMovimentacaoPatrimonio}")
    public ResponseEntity<Movimentacao_Patrimonio> updateMovimentacao(@PathVariable Long idMovimentacaoPatrimonio, @RequestBody Movimentacao_Patrimonio movimentacaoDetails) {
        Optional<Movimentacao_Patrimonio> optionalMovimentacao = movPatrimonioService.findById(idMovimentacaoPatrimonio);
        if (optionalMovimentacao.isPresent()) {
            Movimentacao_Patrimonio movimentacao = optionalMovimentacao.get();
            movimentacao.setSolicitante(movimentacaoDetails.getSolicitante());
            movimentacao.setnPatrimonio(movimentacaoDetails.getnPatrimonio());
            movimentacao.setOrigem(movimentacaoDetails.getOrigem());
            movimentacao.setDestino(movimentacaoDetails.getDestino());
            movimentacao.setDescricao(movimentacaoDetails.getDescricao());
            movimentacao.setTipo(movimentacaoDetails.getTipo());
            movimentacao.setStatus(movimentacaoDetails.getStatus());
            movimentacao.setDataSolicitacao(movimentacaoDetails.getDataSolicitacao());
            movimentacao.setDataAprovacao(movimentacaoDetails.getDataAprovacao());
            Movimentacao_Patrimonio updatedMovimentacao = movPatrimonioService.save(movimentacao);
            return ResponseEntity.ok(updatedMovimentacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idMovimentacaoPatrimonio}")
    public ResponseEntity<Void> deleteMovimentacao(@PathVariable Long idMovimentacaoPatrimonio) {
        Optional<Movimentacao_Patrimonio> optionalMovimentacao = movPatrimonioService.findById(idMovimentacaoPatrimonio);
        if (optionalMovimentacao.isPresent()) {
            movPatrimonioService.deleteById(idMovimentacaoPatrimonio);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
