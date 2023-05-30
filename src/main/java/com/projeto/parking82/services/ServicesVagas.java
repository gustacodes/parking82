package com.projeto.parking82.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.model.Vagas;
import com.projeto.parking82.repository.VagasRepository;

@Service
public class ServicesVagas {
    
    @Autowired
    VagasRepository vagasRepository;

    public Vagas save(Vagas vaga) {
        return vagasRepository.save(vaga);
    }

    public List<Vagas> findAll() {
        return vagasRepository.findAll();
    }

    public Vagas findById(Long id) {
        Optional<Vagas> vagas = vagasRepository.findById(id);
        return vagas.get();
    }

    public void deleteById(Long id) {
        vagasRepository.deleteById(id);
    }

    public Vagas listaVagas(Long vagas) {
        Vagas v = new Vagas();
        v.setVagas(vagas);
        return vagasRepository.save(v);
    }


}
