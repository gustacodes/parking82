package com.projeto.parking82.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.repository.ClienteRepository;

@Service
public class ServicesCliente {

    @Autowired
    ClienteRepository clienteRepository;

    public ServicesCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
   }

   public boolean existsByVaga(String vaga) {
    return clienteRepository.existsByVaga(vaga);
   }

   public boolean existsByPlaca(String placa) {
    return clienteRepository.existsByPlaca(placa);
   }

   
}