package com.projeto.parking82.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.parking82.entities.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    // Métodos personalizados do repositório, se necessário
}
