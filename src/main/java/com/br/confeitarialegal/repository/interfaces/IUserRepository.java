package com.br.confeitarialegal.repository.interfaces;

import com.br.confeitarialegal.entity.User;

/**
 *
 * @author Daniel Vitor
 */
public interface IUserRepository {
  User create(String email, String password);
  boolean login(String email, String password);
  int getLength();
}
