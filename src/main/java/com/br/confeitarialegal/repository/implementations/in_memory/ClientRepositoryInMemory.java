package com.br.confeitarialegal.repository.implementations.in_memory;

import java.util.ArrayList;
import java.util.List;

import com.br.confeitarialegal.entity.Client;
import com.br.confeitarialegal.repository.interfaces.IClientRepository;

/**
 *
 * @author Daniel Vitor
 */
public class ClientRepositoryInMemory implements IClientRepository {

  List<Client> clients;
  
  public ClientRepositoryInMemory() {
    this.clients = new ArrayList<>();
  }
  
  @Override
  public Client create(String name, String cpf, String phonenumber) {
    Client client = new Client(name, cpf, phonenumber);
    client.setId(Double.valueOf(Math.random() * 100).intValue());
    
    this.clients.add(client);
    
    return client;
  }
  
}
