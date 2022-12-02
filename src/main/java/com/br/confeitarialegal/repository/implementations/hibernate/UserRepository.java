package com.br.confeitarialegal.repository.implementations.hibernate;

import javax.persistence.EntityManager;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;

/**
 *
 * @author Daniel Vitor
 */
public class UserRepository implements IUserRepository {

  private final EntityManager entityManager;
  
  public UserRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
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
