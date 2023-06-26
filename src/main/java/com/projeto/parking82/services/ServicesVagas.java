package com.projeto.parking82.services;


import java.util.List;
import java.util.Optional;

import com.projeto.parking82.entities.Cliente;
import com.projeto.parking82.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.entities.Vagas;
import com.projeto.parking82.repository.VagasRepository;

@Service
public class ServicesVagas {
    
    @Autowired
    VagasRepository vagasRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public Vagas registrar(Cliente cliente) {

        var vagas = vagasPorId(cliente.getVaga());
        vagas.setVagas(cliente.getVaga());
        vagas.setStatus(true);
        vagasRepository.save(vagas);
        clienteRepository.save(cliente);

        return vagasRepository.save(vagas);
    }

    public List<Vagas> todasVagas() {
        return vagasRepository.findAll();
    }

    public Vagas vagasPorId(Long id) {
        Optional<Vagas> vagas = vagasRepository.findById(id);
        return vagas.get();
    }

    public Vagas listaVagas(Long vagas) {
        Vagas v = new Vagas();
        v.setVagas(vagas);
        return vagasRepository.save(v);
    }

    public Vagas recibo(Cliente cliente) {

        Vagas vagas = vagasPorId(cliente.getVaga());
        clienteRepository.deleteById(cliente.getId());
        vagas.setStatus(false);
        vagasRepository.save(vagas);

        return null;
    }

}
