package com.example.gerenciamento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerenciamento.Model.Oficina;
import com.example.gerenciamento.Repository.OficinaRepository;

import java.util.Optional;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    public Iterable<Oficina> findAll() {
        return oficinaRepository.findAll();
    }

    public Optional<Oficina> findById(Long idOficina) {
        return oficinaRepository.findById(idOficina);
    }

    public Oficina save(Oficina oficina) {
        return oficinaRepository.save(oficina);
    }

    public void deleteById(Long idOficina) {
        oficinaRepository.deleteById(idOficina);
    }
}
