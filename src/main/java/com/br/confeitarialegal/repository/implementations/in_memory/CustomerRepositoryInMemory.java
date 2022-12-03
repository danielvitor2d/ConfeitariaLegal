package com.br.confeitarialegal.repository.implementations.in_memory;

import java.util.ArrayList;
import java.util.List;

import com.br.confeitarialegal.entity.Customer;
import com.br.confeitarialegal.repository.interfaces.ICustomerRepository;

/**
 *
 * @author Daniel Vitor
 */
public class CustomerRepositoryInMemory implements ICustomerRepository {

  List<Customer> customers;
  
  public CustomerRepositoryInMemory() {
    this.customers = new ArrayList<>();
  }
  
  @Override
  public Customer create(String name, String document, String email, String phonenumber) {
    Customer customer = new Customer(name, document, email, phonenumber);
    customer.setId(Double.valueOf(Math.random() * 100).intValue());
    
    this.customers.add(customer);
    
    return customer;
  }

  @Override
  public List<Customer> list() {
    return this.customers;
  }
}
