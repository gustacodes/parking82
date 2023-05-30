package com.projeto.parking82.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.parking82.model.Vagas;

public interface VagasRepository extends JpaRepository<Vagas, Long> {
    
}
