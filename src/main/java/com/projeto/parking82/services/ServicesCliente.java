package com.projeto.parking82.services;

import com.projeto.parking82.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesCliente {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return  clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> c = clienteRepository.findById(id);
        return c.get();
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByVaga(String vaga) {
        return clienteRepository.existsByVaga(vaga);
    }

    public boolean existsByPlaca(String placa) {
        return clienteRepository.existsByPlaca(placa);
    }


}