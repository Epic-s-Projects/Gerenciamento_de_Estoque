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

import com.example.gerenciamento.Model.Estoque;
import com.example.gerenciamento.Repository.EstoqueRepository;

@Controller
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping("/cadastrar-estoque")
    public String cadastrarEstoque(Estoque estoque, Model model) {
        try {
            estoqueRepository.save(estoque);
            System.out.println("Cadastro realizado com sucesso!");
            return "/interna/interna-funcionario";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar estoque: " + e.getMessage());
            return "/crud/estoque/cadastro-estoque";
        }
    }

    @GetMapping("/gerenciamento-estoque")
    public String listarEstoques(Model model) {
        List<Estoque> estoques = (List<Estoque>) estoqueRepository.findAll();
        model.addAttribute("estoques", estoques);
        return "gerenciamento/gerenciamento-estoque";
    }

    @RequestMapping(value = "/delete-estoque/{idEstoque}", method = RequestMethod.GET)
    public String excluirEstoque(@PathVariable("idEstoque") Long idEstoque) {
        try {
            Estoque estoque = estoqueRepository.findByIdEstoque(idEstoque);
            if (estoque != null) {
                estoqueRepository.delete(estoque);
                System.out.println("Estoque excluído com sucesso!");
            } else {
                System.out.println("Estoque não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Estoque: " + e.getMessage());
        }
        return "redirect:/gerenciamento-estoque";
    }

    @RequestMapping(value = "/edit-estoque/{idEstoque}", method = RequestMethod.GET)
    public ModelAndView editarEstoque(@PathVariable("idEstoque") Long idEstoque) {
        ModelAndView mv = new ModelAndView("crud/estoque/edit-estoque");
        Estoque estoque = estoqueRepository.findByIdEstoque(idEstoque);
        mv.addObject("idEstoque", idEstoque);
        mv.addObject("estoque", estoque);
        return mv;
    }

    @PostMapping("/atualizar-estoque")
    public String atualizarEstoque(@RequestParam("idEstoque") Long idEstoque, Estoque estoque) {
        Estoque estoqueExistente = estoqueRepository.findByIdEstoque(idEstoque);
        if (estoqueExistente != null) {
            estoqueExistente.setIdEstoque(estoque.getIdEstoque());
            estoqueExistente.setNome(estoque.getNome());
            estoqueExistente.setCategoria(estoque.getCategoria());
            estoqueExistente.setQuantidade(estoque.getQuantidade());
            estoqueRepository.save(estoqueExistente);
            return "redirect:/gerenciamento-estoque";
        } else {
            return "redirect:/gerenciamento-estoque";
        }
    }
}
