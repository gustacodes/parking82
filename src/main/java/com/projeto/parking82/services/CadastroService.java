package com.projeto.parking82.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.entities.Cadastro;

import com.projeto.parking82.repository.CadastroRepository;


import java.util.List;


@Service
public class CadastroService {

    @Autowired
    private final CadastroRepository cadastroRepository;


    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    public List<Cadastro> listarCadastros() {
        return cadastroRepository.findAll();
    }

    public void criarCadastro(Cadastro cadastro) {
        cadastroRepository.save(cadastro);
    }

    public boolean verificarLogin(String email, String password) {
        Cadastro cadastro = cadastroRepository.findByEmail(email);
        if (cadastro != null && cadastro.getPassword().equals(password)) {
            return true; // Login válido
        }
        return false; // Login inválido
    }
}
