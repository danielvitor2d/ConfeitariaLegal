package com.br.confeitarialegal.repositories.implementations.in_memory;

import java.util.List;
import java.util.ArrayList;

import com.br.confeitarialegal.entities.User;
import com.br.confeitarialegal.repositories.interfaces.IUserRepository;

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

  @Override
  public int getLength() {
    return this.users.size();
  }

  @Override
  public boolean login(String email, String password) {
    var wrapper = new Object(){ boolean userWasFound = false; };

    this.users.stream().forEach(user -> {
      if (user.getEmail().equals(email) && user.getPassword().equals(password)) wrapper.userWasFound = true;
    });

    return wrapper.userWasFound;
  }


}
