package com.example.gerenciamento.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Model.Sala;
import com.example.gerenciamento.Repository.FuncionarioRepository;
import com.example.gerenciamento.Repository.SalaRepository;

@Controller
public class SalaController {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar-sala")
    public String cadastrarSala(@RequestParam("funcionario.cpf") String cpf, @RequestParam("nSala") String nSala,
            @RequestParam("categoriaSala") String categoriaSala, Model model) {
        try {
            Funcionario funcionarioExistente = funcionarioRepository.findByCpf(cpf);
            if (funcionarioExistente == null) {
                model.addAttribute("erro", "Erro ao cadastrar sala: Funcionário não encontrado");
                return "crud/oficina/cadastro-oficina";
            }

            Sala sala = new Sala();
            sala.setnSala(nSala);
            sala.setCategoriaSala(categoriaSala);
            sala.setFuncionario(funcionarioExistente);

            salaRepository.save(sala);
            System.out.println("Sala cadastrada com sucesso!");
            return "redirect:/interna-funcionario";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar sala: " + e.getMessage());
            return "/crud/sala/cadastro-sala";
        }
    }

    @GetMapping("/gerenciamento-sala")
    public String listarSalas(Model model) {
        List<Sala> salas = (List<Sala>) salaRepository.findAll();
        model.addAttribute("salas", salas);
        return "gerenciamento/gerenciamento-sala";
    }

    @RequestMapping(value = "/delete-sala/{nSala}", method = RequestMethod.GET)
    public String excluirSala(@PathVariable("nSala") String nSala) {
        try {
            Optional<Sala> sala = salaRepository.findBynSala(nSala);
            if (sala.isPresent()) {
                salaRepository.delete(sala.get());
                System.out.println("Sala excluída com sucesso!");
            } else {
                System.out.println("Sala não encontrada para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Sala: " + e.getMessage());
        }
        return "redirect:/gerenciamento-sala";
    }

    @GetMapping("/edit-sala/{nSala}")
    public ModelAndView editarSala(@PathVariable("nSala") String nSala) {
        ModelAndView mv = new ModelAndView("crud/sala/edit-sala");
        Optional<Sala> sala = salaRepository.findBynSala(nSala);
        if (sala.isPresent()) {
            mv.addObject("nSala", nSala);
            mv.addObject("sala", sala.get());
        } else {
            // Lógica para lidar com sala não encontrada, se necessário
        }
        return mv;
    }

    @PostMapping("/atualizar-sala")
    public String atualizarSala(@RequestParam("nSala") String nSala, @RequestParam("cpf") String cpf, @RequestParam("categoria_sala") String categoriaSala) {
        Optional<Sala> salaExistente = salaRepository.findBynSala(nSala);
        if (salaExistente.isPresent()) {
            Sala salaAtualizada = salaExistente.get();
            salaAtualizada.setCategoriaSala(categoriaSala);
            
            Optional<Funcionario> funcionario = funcionarioRepository.findById(cpf);
            if (funcionario.isPresent()) {
                salaAtualizada.setFuncionario(funcionario.get());
            } else {
                // Lógica para lidar com funcionário não encontrado, se necessário
            }

            salaRepository.save(salaAtualizada);
            return "redirect:/gerenciamento-sala";
        } else {
            return "redirect:/gerenciamento-sala";
        }
    }

}