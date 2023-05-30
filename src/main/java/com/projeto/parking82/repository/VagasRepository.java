package com.projeto.parking82.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.parking82.entities.Vagas;
import org.springframework.stereotype.Repository;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {
    
}
