package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Sala;
import com.example.gerenciamento.Repository.SalaRepository;

import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Iterable<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Optional<Sala> findByNSala(String nSala) {
        return salaRepository.findByNSala(nSala);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void deleteByNSala(String nSala) {
        salaRepository.deleteByNSala(nSala);
    }
}
