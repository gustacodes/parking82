package com.projeto.parking82.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.projeto.parking82.model.Cliente;
import com.projeto.parking82.model.Vagas;
import com.projeto.parking82.repository.ClienteRepository;
import com.projeto.parking82.repository.UsuarioRespository;
import com.projeto.parking82.repository.VagasRepository;
import com.projeto.parking82.services.ServicesCliente;
import com.projeto.parking82.services.ServicesVagas;

@RestController
public class Parking82Controller {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    UsuarioRespository usuarioRespository;

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private ServicesCliente servicesCliente;    

    @Autowired
    private ServicesVagas servicesVagas;

    //MÉTODO PARA GERAR A QUANTIDADE DE VAGAS
    @PostMapping("/vagas")
    public ResponseEntity<String> saveVagas() {

        for(int i = 1; i <= 30; i++) {    
            servicesVagas.listaVagas(i);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Vagas criadas");
    }

    //MÉTODO PARA LISTAR TODOS OS CLIENTES
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> clientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    //MÉTODO PARA LISTAR TODAS AS VAGAS DO ESTACIONAMENTO
    @GetMapping("/lista-vagas")
    public ResponseEntity<List<Vagas>> todasVagas() {
        List<Vagas> vaga = vagasRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(vaga);
    }

    //MÉTODO PARA CADASTRAR CLIENTE
    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroCliente(@RequestBody Cliente cliente) {
        
        if(servicesCliente.existsByVaga(cliente.getVaga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga já cadastrada");

        } else if(servicesCliente.existsByPlaca(cliente.getPlaca())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa já cadastrada");

        } else {            

            Iterable<Vagas> lista = vagasRepository.findAll();
            
            for(Vagas va : lista) {
                if(va.getVagas().toString().equals(cliente.getVaga()) && !servicesCliente.existsByVaga(cliente.getVaga())) {

                    va.setVagas(Integer.parseInt(cliente.getVaga()));
                    va.setStatus(true);

                    vagasRepository.deleteById(cliente.getId());
                    vagasRepository.save(va);
                    clienteRepository.save(cliente);

                }
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado");
        }
        
    }

    //MÉTODO PARA FECHAR, EXCLUINDO O CLIENTE E DEIXANDO A VAGA LIVRE (TRUE)
    @DeleteMapping("/fechar/{id}")
    public ResponseEntity<Object> excluirCliente(@PathVariable Long id) {
        
        Optional<Cliente> cliente = clienteRepository.findById(id);
        Optional<Vagas> vagas = vagasRepository.findById(id);

            if(!cliente.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");

            } else {

                clienteRepository.deleteById(id);
                vagas.get().setStatus(false);
                vagasRepository.save(vagas.get());

                return ResponseEntity.status(HttpStatus.OK).body("Fechamento concluído");
            }

    }

}
