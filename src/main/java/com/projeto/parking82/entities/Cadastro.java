package com.projeto.parking82.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cadastros")
public class Cadastro {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String nome;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String senha;
  
  @Column(nullable = false)
  private String confirmacaoSenha;
  
  @Column(nullable = false)
  private boolean termosAceitos;
  
  // Construtores, getters e setters...
  
  public Cadastro() {
    // Construtor vazio necessário para o Hibernate
  }
  
  public Cadastro(String nome, String email, String senha, String confirmacaoSenha, boolean termosAceitos) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.confirmacaoSenha = confirmacaoSenha;
    this.termosAceitos = termosAceitos;
  }
  
  // Getters e setters dos atributos...
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getConfirmacaoSenha() {
    return confirmacaoSenha;
  }

  public void setConfirmacaoSenha(String confirmacaoSenha) {
    this.confirmacaoSenha = confirmacaoSenha;
  }

  public boolean isTermosAceitos() {
    return termosAceitos;
  }

  public void setTermosAceitos(boolean termosAceitos) {
    this.termosAceitos = termosAceitos;
  }
}