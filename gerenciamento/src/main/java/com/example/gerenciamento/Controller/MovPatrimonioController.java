package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Model.Movimentacao_Patrimonio;
import com.example.gerenciamento.Services.FuncionarioService;
import com.example.gerenciamento.Services.MovPatrimonioService;

import java.util.Optional;

@RestController
@RequestMapping("/api/mov_patrimonio")
public class MovPatrimonioController {

    @Autowired
    private MovPatrimonioService movPatrimonioService;

    @Autowired
    private FuncionarioService funcionarioService;

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
public ResponseEntity<?> createMovimentacao(@RequestBody Movimentacao_Patrimonio movimentacaoPatrimonio) {
    // Verifica se o solicitante da movimentação não é nulo e tem um ID válido
    if (movimentacaoPatrimonio.getSolicitante() == null || movimentacaoPatrimonio.getSolicitante().getCpf() == null) {
        // Se o solicitante ou o ID do solicitante forem nulos, retorna um erro com uma mensagem adequada
        return ResponseEntity.badRequest().body("O solicitante da movimentação não foi especificado ou tem um ID inválido.");
    }
    
    // Verifica se o funcionário associado à movimentação já existe no banco de dados
    Optional<Funcionario> optionalFuncionario = funcionarioService.findById(movimentacaoPatrimonio.getSolicitante().getCpf());
    if (optionalFuncionario.isEmpty()) {
        // Se o funcionário não existe, retorna um erro com uma mensagem adequada
        return ResponseEntity.badRequest().body("O funcionário associado à movimentação não existe no banco de dados.");
    }
    
    // Se o funcionário existe, prossegue com a criação da movimentação
    Movimentacao_Patrimonio novaMovimentacao = movPatrimonioService.save(movimentacaoPatrimonio);
    return ResponseEntity.ok(novaMovimentacao);
}


    @PutMapping("/{idMovimentacaoPatrimonio}")
    public ResponseEntity<Movimentacao_Patrimonio> updateMovimentacao(@PathVariable Long idMovimentacaoPatrimonio,
            @RequestBody Movimentacao_Patrimonio movimentacaoDetails) {
        Optional<Movimentacao_Patrimonio> optionalMovimentacao = movPatrimonioService
                .findById(idMovimentacaoPatrimonio);
        if (optionalMovimentacao.isPresent()) {
            Movimentacao_Patrimonio movimentacao = optionalMovimentacao.get();
            movimentacao.setSolicitante(movimentacaoDetails.getSolicitante());
            movimentacao.setIdPatrimonio(movimentacaoDetails.getIdPatrimonio());
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
        Optional<Movimentacao_Patrimonio> optionalMovimentacao = movPatrimonioService
                .findById(idMovimentacaoPatrimonio);
        if (optionalMovimentacao.isPresent()) {
            movPatrimonioService.deleteById(idMovimentacaoPatrimonio);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
