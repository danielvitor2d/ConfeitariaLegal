package com.br.confeitarialegal.entities;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Daniel Vitor
 */
@Entity(name = "library_customers")
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;
  
  @Column(name = "document")
  private String document;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "phone_number")
  private String phoneNumber;

  public Customer() { }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name=" + name + ", document=" + document + ", email=" + email + ", phone-number=" + phoneNumber + '}';
  }
  
  public Customer(String name, String document, String email, String phoneNumber) {
    this.name = name;
    this.document = document;
    this.email = email;
    this.phoneNumber = phoneNumber;
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

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
