package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Services.FuncionarioService;

import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public Iterable<Funcionario> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> getFuncionarioByCpf(@PathVariable String cpf) {
        Optional<Funcionario> funcionario = funcionarioService.findById(cpf);
        if (funcionario.isPresent()) {
            return ResponseEntity.ok(funcionario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("cadastrar-funcionario")
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionarioDetails) {
        Optional<Funcionario> optionalFuncionario = funcionarioService.findById(cpf);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNomeFuncionario(funcionarioDetails.getNomeFuncionario());
            funcionario.setCargo(funcionarioDetails.getCargo());
            funcionario.setEmail(funcionarioDetails.getEmail());
            funcionario.setSenha(funcionarioDetails.getSenha());
            Funcionario updatedFuncionario = funcionarioService.save(funcionario);
            return ResponseEntity.ok(updatedFuncionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable String cpf) {
        Optional<Funcionario> optionalFuncionario = funcionarioService.findById(cpf);
        if (optionalFuncionario.isPresent()) {
            funcionarioService.deleteById(cpf);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
