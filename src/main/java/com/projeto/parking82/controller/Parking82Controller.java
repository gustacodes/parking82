package com.projeto.parking82.controller;

import com.projeto.parking82.entities.Cliente;
import com.projeto.parking82.entities.Vagas;
import com.projeto.parking82.repository.ClienteRepository;
import com.projeto.parking82.services.ServicesCliente;
import com.projeto.parking82.services.ServicesVagas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class Parking82Controller {

    @Autowired
    private ServicesCliente servicesCliente;

    @Autowired
    private ServicesVagas servicesVagas;

    @GetMapping("/vagas")
    public ResponseEntity<List<Vagas>> todasVagas() {
        return ResponseEntity.status(HttpStatus.OK).body(servicesVagas.todasVagas());
    }

    @PostMapping("/vagas/{vagas}")
    public ResponseEntity<String> saveVagas(@PathVariable Long vagas) {

        int count = 0;

        for(long i = 1; i <= vagas; i++) {
            servicesVagas.listaVagas(i);
            count++;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(count + " vagas criadas.");
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> clientes() {
        return ResponseEntity.status(HttpStatus.OK).body(servicesCliente.findAll());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroCliente(@RequestBody Cliente cliente) {

        if(servicesCliente.existsByVaga(cliente.getVaga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga " + cliente.getVaga() + " já está em uso.");

        } else if(servicesCliente.existsByPlaca(cliente.getPlaca())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa " + cliente.getPlaca() + " já cadastrada;");

        } else {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            cliente.setEntrada(LocalTime.now().format(formatter));

            servicesVagas.registrar(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente " + cliente.getNome() + " registrado na vaga " + cliente.getVaga());
        }
    }

    @DeleteMapping("/finalizar/{placa}")
    public ResponseEntity<Object> excluirCliente(@PathVariable String placa) {

        Cliente cliente = servicesCliente.findByPlaca(placa);

        if(cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");

        } else {

            servicesVagas.recibo(cliente);
            LocalDate date = LocalDate.now();
            DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            cliente.setSaida(LocalTime.now().format(formatter));

            servicesCliente.valorTotal(cliente);

            return ResponseEntity.status(HttpStatus.OK).body("\t\t\t\t\t\t\t\t\t--------- RECIBO (PARKING 82) ---------\n\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tDATA: " + LocalDate.now().format(formataData) +
                    "\n\n\t\t\t\t\tCLIENTE: " + cliente.getNome() +
                    "\t\t\t\t\t\tVEÍCULO: " + cliente.getVeiculo() +
                    "\n\t\t\t\t\tPLACA: " + cliente.getPlaca() +
                    "\t\t\t\t\t\t\t\t\tVAGA: " + cliente.getVaga() +
                    "\n\t\t\t\t\tENTRADA: " + cliente.getEntrada() +
                    "\t\t\t\t\t\t\t\t\tSAÍDA: " + cliente.getSaida() +
                    "\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t  PERÍODO: " + cliente.getPeriodo() +
                    "\n\t\t\t\t\t\t\t\t\t\t\t\tÁ PAGAR: R$ "+ cliente.getValor() +
                    "\n\n\t\t\t\tCNPJ: 99.107.370/0001-90 - Contato (82) 98162-1126 - E-mail parking82@contato.com" +
                    "\n\t\t\t\t\t\t\t\tPaking 82 - Soluções em estacionamentos ©");
        }

    }

}
