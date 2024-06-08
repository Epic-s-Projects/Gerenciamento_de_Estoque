package com.example.gerenciamento.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.gerenciamento.Model.Sala;


public interface SalaRepository extends CrudRepository<Sala, String> {
    Optional<Sala> findBynSala(String nSala);
    void deleteBynSala(String nSala);
}
