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
import com.example.gerenciamento.Model.Patrimonio;
import com.example.gerenciamento.Repository.PatrimonioRepository;

@Controller
public class PatrimonioController {
    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @PostMapping("/cadastrar-patrimonio")
    public String cadastrarPatrimonio(Patrimonio patrimonio, Model model) {
        try {
            patrimonioRepository.save(patrimonio);
            System.out.println("Cadastro realizado com sucesso!");
            return "/index";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar patrimonio: " + e.getMessage());
            return "/crud/patrimonio/cadastro-patrimonio";
        }
    }

    @GetMapping("/gerenciamento-patrimonio")
    public String listarPatrimonios(Model model) {
        List<Patrimonio> patrimonios = (List<Patrimonio>) patrimonioRepository.findAll();
        model.addAttribute("patrimonios", patrimonios);
        return "gerenciamento/gerenciamento-patrimonio";
    }

    @RequestMapping(value = "/delete-patrimonio/{idPatrimonio}", method = RequestMethod.GET)
    public String excluirPatrimonio(@PathVariable("idPatrimonio") Long idPatrimonio) {
        try {
            Patrimonio patrimonio = patrimonioRepository.findByIdPatrimonio(idPatrimonio);
            if (patrimonio != null) {
                patrimonioRepository.delete(patrimonio);
                System.out.println("Patrimonio excluído com sucesso!");
            } else {
                System.out.println("Patrimonio não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Patrimonio: " + e.getMessage());
        }
        return "redirect:/gerenciamento-patrimonio";
    }

    @RequestMapping(value = "/edit-patrimonio/{idPatrimonio}", method = RequestMethod.GET)
    public ModelAndView editarPatrimonio(@PathVariable("idPatrimonio") Long idPatrimonio) {
        ModelAndView mv = new ModelAndView("crud/patrimonio/edit-patrimonio");
        Patrimonio patrimonio = patrimonioRepository.findByIdPatrimonio(idPatrimonio);
        mv.addObject("idPatrimonio", idPatrimonio);
        mv.addObject("patrimonio", patrimonio);
        return mv;
    }

    @PostMapping("/atualizar-patrimonio")
    public String atualizarPatrimonio(@RequestParam("idPatrimonio") Long idPatrimonio, Patrimonio patrimonio) {
        Patrimonio patrimonioExistente = patrimonioRepository.findByIdPatrimonio(idPatrimonio);
        if (patrimonioExistente != null) {
            patrimonioExistente.setnPatrimonio(patrimonio.getnPatrimonio());
            patrimonioExistente.setNome(patrimonio.getNome());
            patrimonioExistente.setCategoriaPatrimonio(patrimonio.getCategoriaPatrimonio());
            patrimonioExistente.setStatus(patrimonio.getStatus());
            patrimonioExistente.setSala(patrimonio.getSala());
            patrimonioExistente.setOficina(patrimonio.getOficina());
            patrimonioRepository.save(patrimonioExistente);
            return "redirect:/gerenciamento-patrimonio";
        } else {
            return "redirect:/gerenciamento-patrimonio";
        }
    }
}
