package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Sala;
import com.example.gerenciamento.Services.SalaService;

import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public Iterable<Sala> getAllSalas() {
        return salaService.findAll();
    }

    @GetMapping("/{nSala}")
    public ResponseEntity<Sala> getSalaByNSala(@PathVariable String nSala) {
        Optional<Sala> sala = salaService.findByNSala(nSala);
        if (sala.isPresent()) {
            return ResponseEntity.ok(sala.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Sala createSala(@RequestBody Sala sala) {
        return salaService.save(sala);
    }

    @PutMapping("/{nSala}")
    public ResponseEntity<Sala> updateSala(@PathVariable String nSala, @RequestBody Sala salaDetails) {
        Optional<Sala> optionalSala = salaService.findByNSala(nSala);
        if (optionalSala.isPresent()) {
            Sala sala = optionalSala.get();
            sala.setCpf(salaDetails.getCpf());
            sala.setCategoriaSala(salaDetails.getCategoriaSala());
            Sala updatedSala = salaService.save(sala);
            return ResponseEntity.ok(updatedSala);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{nSala}")
    public ResponseEntity<Void> deleteSala(@PathVariable String nSala) {
        Optional<Sala> optionalSala = salaService.findByNSala(nSala);
        if (optionalSala.isPresent()) {
            salaService.deleteByNSala(nSala);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
