// package com.example.gerenciamento.Controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;
// import com.example.gerenciamento.Model.Sala;
// import com.example.gerenciamento.Repository.SalaRepository;

// @Controller
// public class SalaController {
//     @Autowired
//     private SalaRepository salaRepository;

//     @PostMapping("/cadastrar-sala")
//     public String cadastrarSala(Sala sala, Model model) {
//         try {
//             salaRepository.save(sala);
//             System.out.println("Cadastro realizado com sucesso!");
//             return "/interna/interna-funcionario";
//         } catch (Exception e) {
//             System.out.println("Erro ao cadastrar sala: " + e.getMessage());
//             return "/cadastro/cadastro-sala";
//         }
//     }

//     @GetMapping("/gerenciamento-sala")
//     public String listarSalas(Model model) {
//         List<Sala> salas = (List<Sala>) salaRepository.findAll();
//         model.addAttribute("salas", salas);
//         return "gerenciamento/gerenciamento-sala";
//     }

//     @RequestMapping(value = "/delete-sala/{nSala}", method = RequestMethod.GET)
//     public String excluirSala(@PathVariable("nSala") String nSala) {
//         try {
//             Sala sala = salaRepository.findByNSala(nSala);
//             if (sala != null) {
//                 salaRepository.delete(sala);
//                 System.out.println("Sala excluído com sucesso!");
//             } else {
//                 System.out.println("Sala não encontrado para exclusão");
//             }
//         } catch (Exception e) {
//             System.out.println("Erro ao excluir Sala: " + e.getMessage());
//         }
//         return "redirect:/gerenciamento-sala";
//     }

//     @RequestMapping(value = "/edit-sala/{nSala}", method = RequestMethod.GET)
//     public ModelAndView editarSala(@PathVariable("nSala") String nSala) {
//         ModelAndView mv = new ModelAndView("crud/sala/edit-sala");
//         Sala sala = salaRepository.findByNSala(nSala);
//         mv.addObject("nSala", nSala);
//         mv.addObject("sala", sala);
//         return mv;
//     }

//     @PostMapping("/atualizar-sala")
//     public String atualizarSala(@RequestParam("nSala") String nSala, Sala sala) {
//         Sala salaExistente = salaRepository.findByNSala(nSala);
//         if (salaExistente != null) {
//             salaExistente.setnSala(sala.getnSala());
//             salaExistente.setCpf(sala.getCpf()); // talvez essa parte dê erro
//             salaExistente.setnSala(sala.getCategoriaSala());
//             salaRepository.save(salaExistente);
//             return "redirect:/gerenciamento-sala";
//         } else {
//             return "redirect:/gerenciamento-sala";
//         }
//     }
// }
