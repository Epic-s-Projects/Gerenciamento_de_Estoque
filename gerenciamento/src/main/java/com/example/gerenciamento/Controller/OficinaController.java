package com.example.gerenciamento.Controller;

import java.util.List;

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

import com.example.gerenciamento.Model.Oficina;
import com.example.gerenciamento.Repository.OficinaRepository;

@Controller
public class OficinaController {
    @Autowired
    private OficinaRepository oficinaRepository;

    @PostMapping("/cadastrar-oficina")
    public String cadastrarOficina(Oficina oficina, Model model) {
        try {
            oficinaRepository.save(oficina);
            System.out.println("Cadastro realizado com sucesso!");
            return "crud/oficina/cadastro-oficina";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar oficina: " + e.getMessage());
            return "crud/oficina/cadastro-oficina";
        }
    }

    @GetMapping("/gerenciamento-oficina")
    public String listarOficinas(Model model) {
        List<Oficina> oficinas = (List<Oficina>) oficinaRepository.findAll();
        model.addAttribute("oficinas", oficinas);
        return "gerenciamento/gerenciamento-oficina";
    }

    @RequestMapping(value = "/delete-oficina/{idOficina}", method = RequestMethod.GET)
    public String excluirOficina(@PathVariable("idOficina") Long idOficina) {
        try {
            Oficina oficina = oficinaRepository.findByIdOficina(idOficina);
            if (oficina != null) {
                oficinaRepository.delete(oficina);
                System.out.println("Oficina excluído com sucesso!");
            } else {
                System.out.println("Oficina não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Oficina: " + e.getMessage());
        }
        return "redirect:/gerenciamento-oficina";
    }

    @RequestMapping(value = "/edit-oficina/{idOficina}", method = RequestMethod.GET)
    public ModelAndView editarOficina(@PathVariable("idOficina") Long idOficina) {
        ModelAndView mv = new ModelAndView("crud/oficina/edit-oficina");
        Oficina oficina = oficinaRepository.findByIdOficina(idOficina);
        mv.addObject("idOficina", idOficina);
        mv.addObject("oficina", oficina);
        return mv;
    }

    @PostMapping("/atualizar-oficina")
    public String atualizarOficina(@RequestParam("idOficina") Long idOficina, Oficina oficina) {
        Oficina oficinaExistente = oficinaRepository.findByIdOficina(idOficina);
        if (oficinaExistente != null) {
            oficinaExistente.setCpf(oficina.getCpf()); // talvez essa parte dê erro
            oficinaExistente.setNomeOficina(oficina.getNomeOficina());
            oficinaRepository.save(oficinaExistente);
            return "redirect:/gerenciamento-oficina";
        } else {
            return "redirect:/gerenciamento-oficina";
        }
    }
}
