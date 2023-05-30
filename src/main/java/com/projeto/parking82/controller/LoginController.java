package com.projeto.parking82.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.parking82.entities.Usuario;
import com.projeto.parking82.repository.UsuarioRespository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRespository usuarioRespository;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario user) {

        Usuario usuario = this.usuarioRespository.Login(user.getEmail(), user.getSenha());

            if(usuario != null) {
                return "redirect:/clientes";
            }
                
        model.addAttribute("erro", "Usuário ou senha inválidos.");            
        return "login";            

    }

}
