package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.User;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.UserRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.UserRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.IUserRepository;

/**
 *
 * @author Daniel Vitor
 */
public class UserController {
  IUserRepository repository;
  
  public UserController(RepositoryMethod implementationType) {
    if (implementationType == RepositoryMethod.HIBERNATE) {
      this.repository = UserRepository.getInstance();
    } else {
      this.repository = new UserRepositoryInMemory();
    }
  }
  
  public User create(String email, String password) {
    return this.repository.create(email, password);
  }
  
  public int getLength() {
    return this.repository.getLength();
  }
  
  public boolean login(String email, String password) {
    return this.repository.login(email, password);
  }
}
