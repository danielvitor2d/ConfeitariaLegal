package com.br.confeitarialegal.bean;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ripse
 */
@Entity(name = "clients")
public class Client {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;
  
  @Column(name = "name")
  String name;

  public Client() {
  }

  public Client(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Client{" + "id=" + id + ", name=" + name + '}';
  }
  
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
