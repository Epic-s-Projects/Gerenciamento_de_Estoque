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

import com.example.gerenciamento.Model.Funcionario;
import com.example.gerenciamento.Repository.FuncionarioRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private HttpSession httpSession;

    boolean acessoFuncionario = false;

    @PostMapping("/cadastrar-funcionario") 
    public String cadastrarFuncionarioBD(Funcionario funcionario, Model model) {
        try {
            funcionarioRepository.save(funcionario);
            System.out.println("Cadastro realizado com sucesso!");
            return "/login/login-funcionario";
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
            model.addAttribute("erroSenha", "Sua senha está incorreta");
            return "/cadastro/cadastro-funcionario";
        }
    }

    @GetMapping("/logout-funcionario")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-funcionario";
    }

    @GetMapping("/interna-funcionario")
    public String acessoPageInternaFuncionario() {
        String vaiPara = "";
        if (acessoFuncionario) {
            vaiPara = "interna/interna-funcionario";
        } else {
            vaiPara = "redirect:/login-funcionario";
        }
        return vaiPara;
    }

    @PostMapping("acesso-funcionario")
    public String acessoFuncionario(@RequestParam String cpf, @RequestParam String senha) {
        // método para verificar acesso
        try {
            boolean verificaCpf = funcionarioRepository.existsById(cpf);
            boolean verificaSenha = funcionarioRepository.findByCpf(cpf).getSenha().equals(senha);
            String url = "";
            if (verificaCpf && verificaSenha) {
                Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
                httpSession.setAttribute("funcionario", funcionario);
                httpSession.setAttribute("loggedin", true);
                acessoFuncionario = true;
                url = "redirect:/";
            } else {
                url = "redirect:/login-funcionario";
            }
            return url;
        } catch (Exception e) {
            return "redirect:/login-funcionario";
        }
    }

    @GetMapping("/gerenciamento-funcionario")
    public String listarFuncionarios(Model model) {
        List<Funcionario> funcionarios = (List<Funcionario>) funcionarioRepository.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "gerenciamento/gerenciamento-funcionario";
    }

    @RequestMapping(value = "/delete-funcionario/{cpf}", method = RequestMethod.GET)
    public String excluirFuncionario(@PathVariable("cpf") String cpf) {
        try {
            Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
            if (funcionario != null) {
                funcionarioRepository.delete(funcionario);
                System.out.println("Funcionario excluído com sucesso!");
            } else {
                System.out.println("Funcionario não encontrado para exclusão");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Funcionario: " + e.getMessage());
        }
        return "redirect:/gerenciamento-funcionario";
    }

    @RequestMapping(value = "/edit-funcionario/{cpf}", method = RequestMethod.GET)
    public ModelAndView editarFuncionario(@PathVariable("cpf") String cpf) {
        ModelAndView mv = new ModelAndView("crud/funcionario/edit-funcionario");
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
        mv.addObject("cpf", cpf);
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @PostMapping("/atualizar-funcionario")
    public String atualizarFuncionario(@RequestParam("cpf") String cpf, Funcionario funcionario) {
        Funcionario funcionarioExistente = funcionarioRepository.findByCpf(cpf);
        if (funcionarioExistente != null) {
            funcionarioExistente.setNomeFuncionario(funcionario.getNomeFuncionario());
            funcionarioExistente.setCpf(funcionario.getCpf());
            funcionarioExistente.setCargo(funcionario.getCargo());
            funcionarioExistente.setEmail(funcionario.getEmail());
            funcionarioRepository.save(funcionarioExistente);
            return "redirect:/gerenciamento-funcionario";
        } else {
            return "redirect:/gerenciamento-funcionario";
        }
    }

    @GetMapping("/interna-funcionario/{cpf}")
    public ModelAndView paginaFuncionario(@PathVariable("cpf") String cpf) {
        ModelAndView mv = new ModelAndView("interna/interna-funcionario");
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf);

        if (funcionario != null) {
            mv.addObject("funcionario", funcionario);
        } else {
            
        }

        return mv;
    }


}
