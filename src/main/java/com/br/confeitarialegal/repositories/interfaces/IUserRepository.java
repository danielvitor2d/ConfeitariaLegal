package com.br.confeitarialegal.repositories.interfaces;

import com.br.confeitarialegal.entities.User;

/**
 *
 * @author Daniel Vitor
 */
public interface IUserRepository {
  User create(String email, String password);
  boolean login(String email, String password);
  int getLength();
}
