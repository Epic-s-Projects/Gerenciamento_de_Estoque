package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Movimentacao_Patrimonio;
import com.example.gerenciamento.Repository.MovPatrimonioRepository;

import java.util.Optional;

@Service
public class MovPatrimonioService {

    @Autowired
    private MovPatrimonioRepository movPatrimonioRepository;

    public Iterable<Movimentacao_Patrimonio> findAll() {
        return movPatrimonioRepository.findAll();
    }

    public Optional<Movimentacao_Patrimonio> findById(Long idMovimentacaoPatrimonio) {
        return movPatrimonioRepository.findById(idMovimentacaoPatrimonio);
    }

    public Movimentacao_Patrimonio save(Movimentacao_Patrimonio movimentacao_Patrimonio) {
        return movPatrimonioRepository.save(movimentacao_Patrimonio);
    }

    public void deleteById(Long idMovimentacaoPatrimonio) {
        movPatrimonioRepository.deleteById(idMovimentacaoPatrimonio);
    }
}
