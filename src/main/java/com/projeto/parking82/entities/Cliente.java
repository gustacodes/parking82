package com.projeto.parking82.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Campo obrigatório")
    private String nome;
    @NotEmpty(message = "Campo obrigatório")
    private String veiculo;
    @NotEmpty(message = "Campo obrigatório")
    private String placa;
    @NotNull(message = "Campo obrigatório")
    private Long vaga;
    private String entrada;
    private String saida;
    private String valor = "0.00";
    private String periodo = "00:00";

}
