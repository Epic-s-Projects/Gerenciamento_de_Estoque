package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Estoque;
import com.example.gerenciamento.Repository.EstoqueRepository;

import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Iterable<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> findById(Long idEstoque) {
        return estoqueRepository.findById(idEstoque);
    }

    public Estoque save(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public void deleteById(Long idEstoque) {
        estoqueRepository.deleteById(idEstoque);
    }
}

