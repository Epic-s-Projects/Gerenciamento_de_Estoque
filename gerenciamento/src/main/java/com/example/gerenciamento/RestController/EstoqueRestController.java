package com.example.gerenciamento.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Estoque;
import com.example.gerenciamento.Services.EstoqueService;

import java.util.Optional;

@RestController
@RequestMapping("/api/estoques")

public class EstoqueRestController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public Iterable<Estoque> getAllEstoques() {
        return estoqueService.findAll();
    }

    @GetMapping("/{idEstoque}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Long idEstoque) {
        Optional<Estoque> estoque = estoqueService.findById(idEstoque);
        if (estoque.isPresent()) {
            return ResponseEntity.ok(estoque.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Estoque createEstoque(@RequestBody Estoque estoque) {
        return estoqueService.save(estoque);
    }

    @PutMapping("/{idEstoque}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable Long idEstoque, @RequestBody Estoque estoqueDetails) {
        Optional<Estoque> optionalEstoque = estoqueService.findById(idEstoque);
        if (optionalEstoque.isPresent()) {
            Estoque estoque = optionalEstoque.get();
            estoque.setNome(estoqueDetails.getNome());
            estoque.setDescricao(estoqueDetails.getDescricao());
            estoque.setCategoria(estoqueDetails.getCategoria());
            estoque.setQuantidade(estoqueDetails.getQuantidade());
            Estoque updatedEstoque = estoqueService.save(estoque);
            return ResponseEntity.ok(updatedEstoque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEstoque}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long idEstoque) {
        Optional<Estoque> optionalEstoque = estoqueService.findById(idEstoque);
        if (optionalEstoque.isPresent()) {
            estoqueService.deleteById(idEstoque);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
