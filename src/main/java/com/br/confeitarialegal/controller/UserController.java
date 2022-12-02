package com.br.confeitarialegal.controller;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;

/**
 *
 * @author Daniel Vitor
 */
public class UserController {
  IUserRepository repository;
  
  public UserController(IUserRepository repository) {
    this.repository = repository;
  }
  
  public User create(String email, String password) {
    User user = this.repository.create(email, password);
    return user;
  }
}
