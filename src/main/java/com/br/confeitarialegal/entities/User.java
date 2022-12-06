package com.br.confeitarialegal.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 *
 * @author Daniel Vitor
 */

@Entity(name = "library_users")
@Access(AccessType.PROPERTY)
public class User {
  private SimpleIntegerProperty id = new SimpleIntegerProperty();
  private SimpleStringProperty email;
  private SimpleStringProperty password;

  public User() {
    this.email = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
  }

  public User(String email, String password) {
    this.email = new SimpleStringProperty(email);
    this.password = new SimpleStringProperty(password);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public Integer getId() {
    return this.id.getValue();
  }

  public void setId(int id) {
    this.id.setValue(id);
  }

  public SimpleIntegerProperty idProperty() {
    return this.id;
  }

  @Column(name = "email")
  public String getEmail() {
    return this.email.getValue();
  }

  public void setEmail(String email) {
    this.email.setValue(email);
  }

  public SimpleStringProperty emailProperty() {
    return this.email;
  }

  @Column(name = "password")
  public String getPassword() {
    return this.password.getValue();
  }

  public void setPassword(String password) {
    this.password.setValue(password);
  }

  public SimpleStringProperty passwordProperty() {
    return this.password;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id.getValue() + ", email=" + email.getValue() + ", password=" + password.getValue() + '}';
  }
  
}
