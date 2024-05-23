package com.example.gerenciamento.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.gerenciamento.Model.Sala;


public interface SalaRepository extends CrudRepository<Sala, String>{
    Sala findByNSala(String nSala);
}
