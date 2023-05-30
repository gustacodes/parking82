package com.projeto.parking82.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.model.Vagas;
import com.projeto.parking82.repository.VagasRepository;

@Service
public class ServicesVagas {
    
    @Autowired
    VagasRepository vagasRepository;

    public Vagas listaVagas(Integer vagas) {
        Vagas v = new Vagas();
        v.setVagas(vagas);
        return vagasRepository.save(v);
    }


}
