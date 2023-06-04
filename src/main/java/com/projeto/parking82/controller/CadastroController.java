package com.projeto.parking82.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.parking82.entities.Cadastro;
import com.projeto.parking82.services.CadastroService;

// import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cadastros")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cadastro cadastro) {
        try {
            cadastroService.criarCadastro(cadastro);
            return ResponseEntity.ok("Cadastro criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @GetMapping ("/listar")
    public ResponseEntity<List<Cadastro>> listarCadastros() {
        List<Cadastro> cadastros = cadastroService.listarCadastros();
        return ResponseEntity.ok(cadastros);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        if (cadastroService.verificarLogin(email, password)) {
        return ResponseEntity.ok("Login bem-sucedido");
        } else {
        return ResponseEntity.badRequest().body("Email ou senha inválidos");
        }
    }
}