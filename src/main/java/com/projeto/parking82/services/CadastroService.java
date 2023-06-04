package com.projeto.parking82.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.projeto.parking82.entities.Cadastro;

import com.projeto.parking82.repository.CadastroRepository;

import io.micrometer.common.util.StringUtils;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CadastroService {

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
}
