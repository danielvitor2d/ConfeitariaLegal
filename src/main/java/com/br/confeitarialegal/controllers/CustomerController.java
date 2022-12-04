package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.CustomerRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.CustomerRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.ICustomerRepository;
import java.util.List;

/**
 *
 * @author Daniel Vitor
 */
public class CustomerController {
  ICustomerRepository repository;
  
  public CustomerController(RepositoryMethod implementationType) {
    if (implementationType == RepositoryMethod.HIBERNATE) {
      this.repository = CustomerRepository.getInstance();
    } else {
      this.repository = new CustomerRepositoryInMemory();
    }
  }
  
  public Customer create(String name, String document, String email, String phonenumber) {
    return this.repository.create(name, document, email, phonenumber);
  }

  public Customer get(int id) {
    return this.repository.get(id);
  }

  public List<Customer> list() {
    return this.repository.list();
  }
}
