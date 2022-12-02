package com.br.confeitarialegal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Daniel Vitor
 */
@Entity(name = "library_clients")
public class Client {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "cpf")
  private String cpf;
  
  @Column(name = "phonenumber")
  private String phonenumber;

  public Client() {
    
  }

  public Client(String name, String cpf, String phonenumber) {
    this.name = name;
    this.cpf = cpf;
    this.phonenumber = phonenumber;
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }
}
