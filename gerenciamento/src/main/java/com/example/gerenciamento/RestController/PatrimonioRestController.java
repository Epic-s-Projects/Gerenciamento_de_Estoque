package com.example.gerenciamento.RestController;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/patrimonios")
public class PatrimonioRestController {

    private static final Logger logger = LoggerFactory.getLogger(PatrimonioRestController.class);

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

    @GetMapping("/{idPatrimonio}")
    public ResponseEntity<Patrimonio> getPatrimonioByIdPatrimonio(@PathVariable Long idPatrimonio) {
        logger.info("Recebida requisição GET para buscar patrimônio com idPatrimonio: {}", idPatrimonio);
        Optional<Patrimonio> patrimonio = patrimonioService.findByIdPatrimonio(idPatrimonio);
        if (patrimonio.isPresent()) {
            return ResponseEntity.ok(patrimonio.get());
        } else {
            logger.warn("Patrimônio com idPatrimonio {} não encontrado", idPatrimonio);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patrimonio> createPatrimonio(@RequestBody Patrimonio patrimonio) {
        logger.info("Recebida requisição POST para criar um novo patrimônio");
        if (patrimonio.getIdPatrimonio() == null) {
            logger.error("Falha ao criar patrimônio: nPatrimonio ausente ou vazio");
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Sala> salaOpt = salaService.findByNSala(patrimonio.getSala().getnSala());
        Optional<Oficina> oficinaOpt = oficinaService.findById(patrimonio.getOficina().getIdOficina());

        if (salaOpt.isPresent() && oficinaOpt.isPresent()) {
            patrimonio.setSala(salaOpt.get());
            patrimonio.setOficina(oficinaOpt.get());
            Patrimonio savedPatrimonio = patrimonioService.save(patrimonio);
            logger.info("Patrimônio criado com sucesso: {}", savedPatrimonio);
            return ResponseEntity.ok(savedPatrimonio);
        } else {
            logger.error("Falha ao criar patrimônio: Sala ou Oficina não encontrada");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{idPatrimonio}")
    public ResponseEntity<Patrimonio> updatePatrimonio(@PathVariable Long idPatrimonio, @RequestBody Patrimonio patrimonioDetails) {
        logger.info("Recebida requisição PUT para atualizar o patrimônio com idPatrimonio: {}", idPatrimonio);
        Optional<Patrimonio> optionalPatrimonio = patrimonioService.findByIdPatrimonio(idPatrimonio);
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
            logger.info("Patrimônio atualizado com sucesso: {}", updatedPatrimonio);
            return ResponseEntity.ok(updatedPatrimonio);
        } else {
            logger.warn("Patrimônio com idPatrimonio {} não encontrado para atualização", idPatrimonio);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idPatrimonio}")
    public ResponseEntity<Void> deletePatrimonio(@PathVariable Long idPatrimonio) {
        logger.info("Recebida requisição DELETE para deletar patrimônio com idPatrimonio: {}", idPatrimonio);
        Optional<Patrimonio> optionalPatrimonio = patrimonioService.findByIdPatrimonio(idPatrimonio);
        if (optionalPatrimonio.isPresent()) {
            patrimonioService.deleteByIdPatrimonio(idPatrimonio);
            logger.info("Patrimônio com idPatrimonio {} deletado com sucesso", idPatrimonio);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Patrimônio com idPatrimonio {} não encontrado para deletar", idPatrimonio);
            return ResponseEntity.notFound().build();
        }
    }
}
