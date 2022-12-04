package com.br.confeitarialegal.repositories.implementations.in_memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.repositories.interfaces.ICustomerRepository;

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
  public Customer create(String name, String document, String email, String phoneNumber) {
    Customer customer = new Customer(name, document, email, phoneNumber);
    customer.setId(Double.valueOf(Math.random() * 100).intValue());

    this.customers.add(customer);

    return customer;
  }

  @Override
  public Customer get(int id) {
    Optional<Customer> result = this.customers.stream().filter(product -> product.getId() == id).findFirst();
    return result.orElse(null);
  }

  @Override
  public List<Customer> list() {
    return this.customers;
  }
}
