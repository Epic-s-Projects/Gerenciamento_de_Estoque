package com.example.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gerenciamento.Model.Oficina;
import com.example.gerenciamento.Model.Patrimonio;
import com.example.gerenciamento.Model.Sala;
import com.example.gerenciamento.Services.OficinaService;
import com.example.gerenciamento.Services.PatrimonioService;
import com.example.gerenciamento.Services.SalaService;

import java.util.Optional;

@RestController
@RequestMapping("/api/patrimonios")
public class PatrimonioController {

    @Autowired
    private PatrimonioService patrimonioService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    public Iterable<Patrimonio> getAllPatrimonios() {
        return patrimonioService.findAll();
    }

    @GetMapping("/{nPatrimonio}")
    public ResponseEntity<Patrimonio> getPatrimonioByNPatrimonio(@PathVariable String nPatrimonio) {
        Optional<Patrimonio> patrimonio = patrimonioService.findByNPatrimonio(nPatrimonio);
        if (patrimonio.isPresent()) {
            return ResponseEntity.ok(patrimonio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patrimonio> createPatrimonio(@RequestBody Patrimonio patrimonio) {
        if (patrimonio.getNPatrimonio() == null || patrimonio.getNPatrimonio().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Sala> salaOpt = salaService.findByNSala(patrimonio.getSala().getnSala());
        Optional<Oficina> oficinaOpt = oficinaService.findById(patrimonio.getOficina().getIdOficina());

        if (salaOpt.isPresent() && oficinaOpt.isPresent()) {
            patrimonio.setSala(salaOpt.get());
            patrimonio.setOficina(oficinaOpt.get());
            Patrimonio savedPatrimonio = patrimonioService.save(patrimonio);
            return ResponseEntity.ok(savedPatrimonio);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{nPatrimonio}")
    public ResponseEntity<Patrimonio> updatePatrimonio(@PathVariable String nPatrimonio, @RequestBody Patrimonio patrimonioDetails) {
        Optional<Patrimonio> optionalPatrimonio = patrimonioService.findByNPatrimonio(nPatrimonio);
        if (optionalPatrimonio.isPresent()) {
            Patrimonio patrimonio = optionalPatrimonio.get();
            patrimonio.setNome(patrimonioDetails.getNome());
            patrimonio.setDescricao(patrimonioDetails.getDescricao());
            patrimonio.setCategoriaPatrimonio(patrimonioDetails.getCategoriaPatrimonio());
            patrimonio.setStatus(patrimonioDetails.getStatus());
            patrimonio.setUltimaManutencao(patrimonioDetails.getUltimaManutencao());
            patrimonio.setSala(patrimonioDetails.getSala());
            patrimonio.setOficina(patrimonioDetails.getOficina());
            Patrimonio updatedPatrimonio = patrimonioService.save(patrimonio);
            return ResponseEntity.ok(updatedPatrimonio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{nPatrimonio}")
    public ResponseEntity<Void> deletePatrimonio(@PathVariable String nPatrimonio) {
        Optional<Patrimonio> optionalPatrimonio = patrimonioService.findByNPatrimonio(nPatrimonio);
        if (optionalPatrimonio.isPresent()) {
            patrimonioService.deleteByNPatrimonio(nPatrimonio);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
