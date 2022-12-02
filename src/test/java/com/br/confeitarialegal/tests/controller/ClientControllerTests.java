package com.br.confeitarialegal.tests.controller;

import org.junit.Test;

import com.br.confeitarialegal.entity.Client;
import com.br.confeitarialegal.controller.ClientController;
import com.br.confeitarialegal.repository.implementations.in_memory.ClientRepositoryInMemory;
import com.br.confeitarialegal.repository.interfaces.IClientRepository;

/**
 *
 * @author Daniel Vitor
 */
public class ClientControllerTests {
  
  @Test
  public void shoud_be_able_to_create_a_client() {
    IClientRepository clientRepository = new ClientRepositoryInMemory();
    
    ClientController clientController = new ClientController(clientRepository);

    // name, cpf, phonenumber
    Client client = clientController.create("Daniel", "048.809.450-05", "88996134386");
    
    assert(client.getId() != null);
  }
  
}
