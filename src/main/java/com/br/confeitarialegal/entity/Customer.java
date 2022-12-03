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
  
  @Column(name = "phonenumber")
  private String phonenumber;

  public Customer() {
    
  }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name=" + name + ", document=" + document + ", email=" + email + ", phonenumber=" + phonenumber + '}';
  }
  
  public Customer(String name, String document, String email, String phonenumber) {
    this.name = name;
    this.document = document;
    this.email = email;
    this.phonenumber = phonenumber;
  }
  
  public Customer(int id, String name, String document, String email, String phonenumber) {
    this.id = id;
    this.name = name;
    this.document = document;
    this.email = email;
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
  
  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }
}
