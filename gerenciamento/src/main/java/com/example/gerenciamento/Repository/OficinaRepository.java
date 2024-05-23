package com.example.gerenciamento.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.gerenciamento.Model.Oficina;


public interface OficinaRepository extends CrudRepository<Oficina, Long>{
    Oficina findByIdOficina(String idOficina);
}
