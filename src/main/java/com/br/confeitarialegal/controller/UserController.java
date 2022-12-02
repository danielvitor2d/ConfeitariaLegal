package com.br.confeitarialegal.controller;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.repository.RepositoryMethod;
import com.br.confeitarialegal.repository.implementations.hibernate.UserRepository;
import com.br.confeitarialegal.repository.implementations.in_memory.UserRepositoryInMemory;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;

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
    User user = this.repository.create(email, password);
    return user;
  }
  
  public int getLength() {
    return this.repository.getLength();
  }
  
  public boolean login(String email, String password) {
    return this.repository.login(email, password);
  }
}
