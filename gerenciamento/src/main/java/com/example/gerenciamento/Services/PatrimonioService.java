package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Patrimonio;
import com.example.gerenciamento.Repository.PatrimonioRepository;

import java.util.Optional;

@Service
public class PatrimonioService {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    public Iterable<Patrimonio> findAll() {
        return patrimonioRepository.findAll();
    }

    public Optional<Patrimonio> findByIdPatrimonio(Long idPatrimonio) {
        return patrimonioRepository.findById(idPatrimonio);
    }

    public Patrimonio save(Patrimonio patrimonio) {
        return patrimonioRepository.save(patrimonio);
    }

    public void deleteByIdPatrimonio(Long idPatrimonio) {
        patrimonioRepository.deleteById(idPatrimonio);
    }
}
