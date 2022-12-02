package com.br.confeitarialegal.repository.interfaces;

import com.br.confeitarialegal.entity.Client;

/**
 *
 * @author Daniel Vitor
 */
public interface IClientRepository {
  Client create(String name, String cpf, String phonenumber);
}
