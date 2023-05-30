package com.projeto.parking82.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.parking82.model.Cliente;
import com.projeto.parking82.model.Vagas;
import com.projeto.parking82.repository.ClienteRepository;
import com.projeto.parking82.repository.UsuarioRespository;
import com.projeto.parking82.repository.VagasRepository;
import com.projeto.parking82.services.ServicesCliente;
import com.projeto.parking82.services.ServicesVagas;

@Controller
public class Parking82Controller {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    UsuarioRespository usuarioRespository;

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private ServicesCliente servicesCliente;    

    @Autowired
    private ServicesVagas servicesVagas;

    @RequestMapping("/vagas")
    public String saveVagas() {

        for(int i = 1; i <= 30; i++) {    
              servicesVagas.listaVagas(i);
        }            

        return "redirect:/lista-vagas";
    }

    @RequestMapping("/clientes")
    public ModelAndView clientes() {

        ModelAndView mv = new ModelAndView("clientes");
        List<Cliente> clientes = clienteRepository.findAll();
        
        mv.addObject("cliente", clientes);        

        return mv;
    }

    @GetMapping("/cadastro")
    public String cadastro() { 
        return "cadastro";
    }

    @RequestMapping("/cadastro")
    public String cadastroCliente(Cliente cliente, Model model) {
        
        if(servicesCliente.existsByVaga(cliente.getVaga())) {
            model.addAttribute("vagaExiste", "Esta vaga já está em uso");
            return "cadastro";

        } else if(servicesCliente.existsByPlaca(cliente.getPlaca())) {
            model.addAttribute("placaExiste", "Esta placa já está cadastrada");
            return "cadastro";      

        } else {            

            Iterable<Vagas> lista = vagasRepository.findAll();
            
            for(Vagas va : lista) {
                if(va.getVagas().toString().equals(cliente.getVaga()) && !servicesCliente.existsByVaga(cliente.getVaga())) {

                    va.setVagas(Integer.parseInt(cliente.getVaga()));
                    va.setStatus(true);

                    vagasRepository.deleteById(cliente.getId());
                    vagasRepository.save(va);
                    clienteRepository.save(cliente);

                }
            }

            return "redirect:/clientes";
        }
        
    }

    @GetMapping("/lista-vagas")
    public ModelAndView getNumeros() {
        
        Iterable<Vagas> vaga = vagasRepository.findAll();
        ModelAndView mv = new ModelAndView("vagas");
                
        mv.addObject("vaga", vaga);

        return mv;

    }

    @RequestMapping("/deletar")
    public String excluirCliente(long id) {
        
        if(id > 0) {         

            Iterable<Vagas> deleteVagas =  vagasRepository.findAll();
            Iterable<Cliente> deleteCliente =  clienteRepository.findAll();

            for(Cliente c : deleteCliente) {

                for(Vagas vaga : deleteVagas) {

                    if(vaga.getVagas().toString().equals(c.getVaga())) {

                        if(id == c.getId()) {
                            vaga.setStatus(false);
                            vagasRepository.save(vaga);
                        }
                        
                        clienteRepository.deleteById(id);
                    }

                }

            }
            
        }

        return "redirect:/clientes";
    }    

    @RequestMapping("/password")
    public String password() {
        return "password";
    }


}
