package com.projeto.parking82.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Campo obrigatório")
    private Long idUsuario;

    @NotEmpty(message = "Campo obrigatório")
    private String email;

    @NotEmpty(message = "Campo obrigatório")
    private String senha;

    @NotEmpty(message = "Campo obrigatório")
    private String ConfirmedPassword;

    @NotEmpty(message = "Campo obrigatório")
    private String name;

    @NotEmpty(message = "Campo obrigatório")
    private String lastName;

    @NotEmpty(message = "Campo obrigatório")
    private Boolean checkBox;


}
