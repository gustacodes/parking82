package com.projeto.parking82.model;

import java.io.Serializable;

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


}
