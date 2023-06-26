package com.projeto.parking82.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.parking82.entities.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
   boolean existsByVaga(Long vaga);
   boolean existsByPlaca(String placa);
   Cliente findByPlaca(String placa);
   
}
