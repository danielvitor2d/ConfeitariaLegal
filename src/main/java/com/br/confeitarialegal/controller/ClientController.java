package com.br.confeitarialegal.controller;

import com.br.confeitarialegal.repository.interfaces.IClientRepository;
import com.br.confeitarialegal.entity.Client;

/**
 *
 * @author Daniel Vitor
 */
public class ClientController {
  IClientRepository repository;
  
  public ClientController(IClientRepository clientRepository) {
    this.repository = clientRepository;
  }
  
  public Client create(String name, String cpf, String phonenumber) {
    Client client = this.repository.create(name, cpf, phonenumber);
    return client;
  }
}
