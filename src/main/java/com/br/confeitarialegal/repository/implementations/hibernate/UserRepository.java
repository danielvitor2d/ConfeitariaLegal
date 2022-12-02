package com.br.confeitarialegal.repository.implementations.hibernate;

import javax.persistence.EntityManager;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;

/**
 *
 * @author Daniel Vitor
 */
public class UserRepository implements IUserRepository {
  private static UserRepository instance;

  private final EntityManager entityManager;

    private UserRepository() {
      this.entityManager = Connection.getInstance().create();
  }

  public static UserRepository getInstance() {
    if (instance == null) {
      instance = new UserRepository();
    }
    return instance;
  }

  @Override
  public User create(String email, String password) {
    User user = new User(email, password);
    this.entityManager.getTransaction().begin();
    this.entityManager.persist(user);
    this.entityManager.getTransaction().commit();
    return user;
  }
  
}
