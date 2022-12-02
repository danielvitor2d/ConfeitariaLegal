package com.br.confeitarialegal.repository.implementations.in_memory;

import java.util.List;
import java.util.ArrayList;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;

/**
 *
 * @author Daniel Vitor
 */
public class UserRepositoryInMemory implements IUserRepository {
  List<User> users;
  
  public UserRepositoryInMemory() {
    this.users = new ArrayList<>();
  }

  @Override
  public User create(String email, String password) {
    User user = new User(email, password);
    user.setId(Double.valueOf(Math.random() * 100).intValue());
    
    this.users.add(user);
    
    return user;
  }
}
