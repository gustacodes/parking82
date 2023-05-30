package com.projeto.parking82.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.parking82.entities.Cliente;
import com.projeto.parking82.entities.Vagas;
import com.projeto.parking82.services.ServicesCliente;
import com.projeto.parking82.services.ServicesVagas;

@RestController
public class Parking82Controller {

    @Autowired
    private ServicesCliente servicesCliente;

    @Autowired
    private ServicesVagas servicesVagas;

    //MÉTODO PARA GERAR A QUANTIDADE DE VAGAS
    @PostMapping("/vagas")
    public ResponseEntity<String> saveVagas() {

        int count = 0;

        for(long i = 1; i <= 30; i++) {
            servicesVagas.listaVagas(i);
            count++;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(count + " vagas criadas.");
    }

    //MÉTODO PARA LISTAR TODOS OS CLIENTES
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> clientes() {
        return ResponseEntity.status(HttpStatus.OK).body(servicesCliente.findAll());
    }

    //MÉTODO PARA LISTAR TODAS AS VAGAS DO ESTACIONAMENTO
    @GetMapping("/lista-vagas")
    public ResponseEntity<List<Vagas>> todasVagas() {
        return ResponseEntity.status(HttpStatus.OK).body(servicesVagas.findAll());
    }

    //MÉTODO PARA CADASTRAR CLIENTE
    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroCliente(@RequestBody Cliente cliente) {

        if(servicesCliente.existsByVaga(cliente.getVaga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga " + cliente.getVaga() + " já está em uso.");

        } else if(servicesCliente.existsByPlaca(cliente.getPlaca())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa " + cliente.getPlaca() + " já cadastrada;");

        } else {

            var vagas = servicesVagas.findById(cliente.getVaga());
            vagas.setVagas(cliente.getVaga());
            vagas.setStatus(true);
            servicesVagas.save(vagas);
            servicesCliente.save(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente " + cliente.getNome() + " registrado na vaga " + vagas.getVagas());
        }
    }

    //MÉTODO PARA FECHAR, EXCLUINDO O CLIENTE E DEIXANDO A VAGA LIVRE (TRUE)
    @DeleteMapping("/fechar/{id}")
    public ResponseEntity<Object> excluirCliente(@PathVariable Long id) {

        Cliente cliente = servicesCliente.findById(id);
        Vagas vagas = servicesVagas.findById(cliente.getVaga());

        if(cliente.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");

        } else {

            servicesCliente.deleteById(id);
            vagas.setStatus(false);
            servicesVagas.save(vagas);

            return ResponseEntity.status(HttpStatus.OK).body("Fechamento concluído");
        }

    }

}
