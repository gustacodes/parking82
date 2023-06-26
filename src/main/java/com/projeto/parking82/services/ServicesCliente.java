package com.projeto.parking82.services;

import com.projeto.parking82.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.parking82.repository.ClienteRepository;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesCliente {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return  clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findByPlaca(String placa) {
        Cliente c = clienteRepository.findByPlaca(placa);
        return c;
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByVaga(Long vaga) {
        return clienteRepository.existsByVaga(vaga);
    }

    public boolean existsByPlaca(String placa) {
        return clienteRepository.existsByPlaca(placa);
    }

    public Cliente valorTotal(Cliente cliente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime entrada = LocalTime.parse(cliente.getEntrada(), formatter);
        LocalTime saida = LocalTime.parse(cliente.getSaida(), formatter);

        Duration duration = Duration.between(entrada, saida);
        long minutes = duration.toMinutes();
        double total = minutes * 0.0233333333333333;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String totalFormatado = decimalFormat.format(total);

        if(minutes <= 300){
            cliente.setValor("7.00");
        } else {
            cliente.setValor(totalFormatado);
        }

        return cliente;
    }

}