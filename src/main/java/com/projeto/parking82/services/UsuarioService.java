package com.projeto.parking82.services;

import org.springframework.stereotype.Service;

import com.projeto.parking82.entities.Usuario;
import com.projeto.parking82.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        // Implemente qualquer lógica de negócio necessária aqui, antes de salvar o usuário no banco de dados.
        return usuarioRepository.save(usuario);
    }
}