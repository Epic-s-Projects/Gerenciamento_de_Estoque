package com.example.gerenciamento.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Movimentacao_Estoque;
import com.example.gerenciamento.Services.MovEstoqueService;

import java.util.Optional;

@RestController
@RequestMapping("/api/mov_estoque")
public class MovEstoqueRestController {

    @Autowired
    private MovEstoqueService movEstoqueService;

    @GetMapping
    public Iterable<Movimentacao_Estoque> getAllMovimentacoes() {
        return movEstoqueService.findAll();
    }

    @GetMapping("/{idMovimentacaoEstoque}")
    public ResponseEntity<Movimentacao_Estoque> getMovimentacaoById(@PathVariable Long idMovimentacaoEstoque) {
        Optional<Movimentacao_Estoque> movimentacao = movEstoqueService.findById(idMovimentacaoEstoque);
        if (movimentacao.isPresent()) {
            return ResponseEntity.ok(movimentacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Movimentacao_Estoque createMovimentacao(@RequestBody Movimentacao_Estoque movimentacaoEstoque) {
        return movEstoqueService.save(movimentacaoEstoque);
    }

    @PutMapping("/{idMovimentacaoEstoque}")
    public ResponseEntity<Movimentacao_Estoque> updateMovimentacao(@PathVariable Long idMovimentacaoEstoque, @RequestBody Movimentacao_Estoque movimentacaoDetails) {
        Optional<Movimentacao_Estoque> optionalMovimentacao = movEstoqueService.findById(idMovimentacaoEstoque);
        if (optionalMovimentacao.isPresent()) {
            Movimentacao_Estoque movimentacao = optionalMovimentacao.get();
            movimentacao.setSolicitante(movimentacaoDetails.getSolicitante());
            movimentacao.setIdEstoque(movimentacaoDetails.getIdEstoque());
            movimentacao.setDescricao(movimentacaoDetails.getDescricao());
            movimentacao.setTipo(movimentacaoDetails.getTipo());
            movimentacao.setStatus(movimentacaoDetails.getStatus());
            movimentacao.setDataSolicitacao(movimentacaoDetails.getDataSolicitacao());
            movimentacao.setDataAprovacao(movimentacaoDetails.getDataAprovacao());
            Movimentacao_Estoque updatedMovimentacao = movEstoqueService.save(movimentacao);
            return ResponseEntity.ok(updatedMovimentacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idMovimentacaoEstoque}")
    public ResponseEntity<Void> deleteMovimentacao(@PathVariable Long idMovimentacaoEstoque) {
        Optional<Movimentacao_Estoque> optionalMovimentacao = movEstoqueService.findById(idMovimentacaoEstoque);
        if (optionalMovimentacao.isPresent()) {
            movEstoqueService.deleteById(idMovimentacaoEstoque);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
