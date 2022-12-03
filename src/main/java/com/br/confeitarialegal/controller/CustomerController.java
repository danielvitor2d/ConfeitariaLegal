package com.br.confeitarialegal.controller;

import com.br.confeitarialegal.entity.Customer;
import com.br.confeitarialegal.repository.RepositoryMethod;
import com.br.confeitarialegal.repository.implementations.hibernate.CustomerRepository;
import com.br.confeitarialegal.repository.implementations.in_memory.CustomerRepositoryInMemory;
import com.br.confeitarialegal.repository.interfaces.ICustomerRepository;
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
    Customer customer = this.repository.create(name, document, email, phonenumber);
    return customer;
  }

  public List<Customer> list() {
    return this.repository.list();
  }
}
