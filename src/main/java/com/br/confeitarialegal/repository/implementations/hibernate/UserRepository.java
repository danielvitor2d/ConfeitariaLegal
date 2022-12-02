package com.br.confeitarialegal.repository.implementations.hibernate;

import javax.persistence.EntityManager;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;
import javax.persistence.Query;

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
    try {
      User user = new User(email, password);
      this.entityManager.getTransaction().begin();
      this.entityManager.persist(user);
      return user;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      this.entityManager.getTransaction().rollback();
    } finally {
      this.entityManager.getTransaction().commit();
    }
    return null;
  }

  @Override
  public int getLength() {
    this.entityManager.getTransaction().begin();
    Long  length = (Long) this.entityManager.createQuery("SELECT COUNT(id) FROM library_users").getSingleResult();
    this.entityManager.getTransaction().commit();
    return length.intValue();
  }

  @Override
  public boolean login(String email, String password) {
    this.entityManager.getTransaction().begin();
    Query query = this.entityManager.createQuery("SELECT password FROM library_users WHERE email = :email");
    query.setParameter("email", email);
    String passwordDB = (String)query.getSingleResult();
    this.entityManager.getTransaction().commit();
    return (password.equals(passwordDB));
  }
  
}
