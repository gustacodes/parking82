package com.projeto.parking82.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.parking82.model.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

    @Query(value =  "SELECT * FROM usuario WHERE email =:email and senha =:senha", nativeQuery = true)
    public Usuario Login(String email, String senha);
    
}
