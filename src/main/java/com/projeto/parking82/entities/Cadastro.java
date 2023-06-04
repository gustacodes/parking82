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
  private String name;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String password;
  
  @Column(nullable = false)
  private String confirmPassword;
  
  @Column(nullable = false)
  private boolean acceptTerms;
  
  // Construtores, getters e setters...
  
  public Cadastro() {
    // Construtor vazio necessário para o Hibernate
  }
  
  public Cadastro(String name, String email, String password, String confirmPassword, boolean acceptTerms) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.acceptTerms = acceptTerms;
  }
  
  // Getters e setters dos atributos...
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getconfirmPassword() {
    return confirmPassword;
  }

  public void setconfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public boolean isacceptTerms() {
    return acceptTerms;
  }

  public void setacceptTerms(boolean acceptTerms) {
    this.acceptTerms = acceptTerms;
  }
}