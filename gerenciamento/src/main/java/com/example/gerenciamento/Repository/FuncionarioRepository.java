package com.example.gerenciamento.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.gerenciamento.Model.Funcionario;


public interface FuncionarioRepository extends CrudRepository<Funcionario, String>{
    Funcionario findByCpf(String cpf);
}
