package com.br.confeitarialegal.repository.implementations.hibernate;

import com.br.confeitarialegal.entity.Client;
import com.br.confeitarialegal.repository.interfaces.IClientRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author Daniel Vitor
 */
public class ClientRepository implements IClientRepository {

  private final EntityManager entityManager;
  
  public ClientRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  @Override
  public Client create(String name, String cpf, String phonenumber) {
    Client client = new Client(name, cpf, phonenumber);
    this.entityManager.getTransaction().begin();
    this.entityManager.persist(client);
    this.entityManager.getTransaction().commit();
    return client;
  }
  
}
