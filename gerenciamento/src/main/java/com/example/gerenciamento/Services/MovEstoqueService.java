package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Movimentacao_Estoque;
import com.example.gerenciamento.Repository.MovEstoqueRepository;

import java.util.Optional;

@Service
public class MovEstoqueService {

    @Autowired
    private MovEstoqueRepository movEstoqueRepository;

    public Iterable<Movimentacao_Estoque> findAll() {
        return movEstoqueRepository.findAll();
    }

    public Optional<Movimentacao_Estoque> findById(Long idMovimentacaoEstoque) {
        return movEstoqueRepository.findById(idMovimentacaoEstoque);
    }

    public Movimentacao_Estoque save(Movimentacao_Estoque movimentacao_Estoque) {
        return movEstoqueRepository.save(movimentacao_Estoque);
    }

    public void deleteById(Long idMovimentacaoEstoque) {
        movEstoqueRepository.deleteById(idMovimentacaoEstoque);
    }
}
