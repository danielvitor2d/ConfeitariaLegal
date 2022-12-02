package com.br.confeitarialegal.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danielvitor
 */
public class Connection {
  private static Connection instance;
  
  private static EntityManagerFactory factory;

  private Connection() {
  }

  public static Connection getInstance() {
    if (instance == null) {
      instance = new Connection();
    }
    return instance;
  }
  
  public EntityManager create() {
    EntityManager entityManager = null;
    try {
      if (factory == null) {
        factory = Persistence.createEntityManagerFactory("persistencia_postgres_confeitaria_legal");
      }
      entityManager = factory.createEntityManager();
      System.out.println("Entity Manager criado = " + entityManager);
    } catch (Exception e) {
      System.out.println("Erro ao se conectar ao Banco de Dados");
    }
    return entityManager;
  }
}
