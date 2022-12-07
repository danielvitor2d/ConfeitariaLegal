package com.br.confeitarialegal.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Daniel Vitor
 */
@Entity(name = "library_customers")
@Access(AccessType.PROPERTY)
public class Customer {
  private SimpleIntegerProperty id = new SimpleIntegerProperty();
  private SimpleStringProperty name;
  private SimpleStringProperty document;
  private SimpleStringProperty email;
  private SimpleStringProperty phoneNumber;

  public Customer() {
    this.name = new SimpleStringProperty("");
    this.document = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.phoneNumber = new SimpleStringProperty("");
  }

  @Override
  public String toString() {
    return this.getName();
//    return "Customer{" + "id=" + id.getValue() + ", name=" + name.getValue() + ", document=" + document.getValue() + ", email=" + email.getValue() + ", phone-number=" + phoneNumber.getValue() + '}';
  }
  
  public Customer(String name, String document, String email, String phoneNumber) {
    this.name = new SimpleStringProperty(name);
    this.document = new SimpleStringProperty(document);
    this.email = new SimpleStringProperty(email);
    this.phoneNumber = new SimpleStringProperty(phoneNumber);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
  @SequenceGenerator(name="customer_generator", sequenceName = "customer_seq", allocationSize = 1)
  @Column(name = "id")
  public Integer getId() {
    return id.getValue();
  }

  public void setId(int id) {
    this.id.setValue(id);
  }

  public SimpleIntegerProperty idProperty() {
    return this.id;
  }

  @Column(name = "name")
  public String getName() {
    return name.getValue();
  }

  @Column(name = "document")
  public void setName(String name) {
    this.name.setValue(name);
  }

  public SimpleStringProperty nameProperty() {
    return this.name;
  }

  public String getDocument() {
    return document.getValue();
  }

  public void setDocument(String document) {
    this.document.setValue(document);
  }

  public SimpleStringProperty documentProperty() {
    return this.document;
  }

  @Column(name = "email")
  public String getEmail() {
    return email.getValue();
  }

  public void setEmail(String email) {
    this.email.setValue(email);
  }

  public SimpleStringProperty emailProperty() {
    return this.email;
  }

  @Column(name = "phone_number")
  public String getPhoneNumber() {
    return phoneNumber.getValue();
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber.setValue(phoneNumber);
  }

  public SimpleStringProperty phoneNumberProperty() {
    return this.phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;

    Customer customer = (Customer) o;

    if (!Objects.equals(id.getValue(), customer.id.getValue())) return false;
    if (!Objects.equals(name.getValue(), customer.name.getValue())) return false;
    if (!Objects.equals(document.getValue(), customer.document.getValue())) return false;
    if (!Objects.equals(email.getValue(), customer.email.getValue())) return false;
    return Objects.equals(phoneNumber.getValue(), customer.phoneNumber.getValue());
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (document != null ? document.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    return result;
  }

  public Customer createClone() {
    Customer customer = new Customer();
    customer.setId(this.getId());
    customer.setDocument(this.getDocument());
    customer.setEmail(this.getEmail());
    customer.setName(this.getName());
    customer.setPhoneNumber(this.getPhoneNumber());
    return customer;
  }

  public void clone(Customer customer) {
    this.setId(customer.getId());
    this.setDocument(customer.getDocument());
    this.setEmail(customer.getEmail());
    this.setName(customer.getName());
    this.setPhoneNumber(customer.getPhoneNumber());
  }
}
