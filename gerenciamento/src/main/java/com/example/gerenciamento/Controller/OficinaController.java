package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Oficina;
import com.example.gerenciamento.Services.OficinaService;

import java.util.Optional;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    public Iterable<Oficina> getAllOficinas() {
        return oficinaService.findAll();
    }

    @GetMapping("/{idOficina}")
    public ResponseEntity<Oficina> getOficinaById(@PathVariable Long idOficina) {
        Optional<Oficina> oficina = oficinaService.findById(idOficina);
        if (oficina.isPresent()) {
            return ResponseEntity.ok(oficina.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Oficina createOficina(@RequestBody Oficina oficina) {
        return oficinaService.save(oficina);
    }

    @PutMapping("/{idOficina}")
    public ResponseEntity<Oficina> updateOficina(@PathVariable Long idOficina, @RequestBody Oficina oficinaDetails) {
        Optional<Oficina> optionalOficina = oficinaService.findById(idOficina);
        if (optionalOficina.isPresent()) {
            Oficina oficina = optionalOficina.get();
            oficina.setNomeOficina(oficinaDetails.getNomeOficina());
            oficina.setCpf(oficinaDetails.getCpf());
            Oficina updatedOficina = oficinaService.save(oficina);
            return ResponseEntity.ok(updatedOficina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idOficina}")
    public ResponseEntity<Void> deleteOficina(@PathVariable Long idOficina) {
        Optional<Oficina> optionalOficina = oficinaService.findById(idOficina);
        if (optionalOficina.isPresent()) {
            oficinaService.deleteById(idOficina);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
