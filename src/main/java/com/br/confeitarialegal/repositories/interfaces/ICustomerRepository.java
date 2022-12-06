package com.br.confeitarialegal.repositories.interfaces;

import java.util.List;

import com.br.confeitarialegal.entities.Customer;

/**
 *
 * @author Daniel Vitor
 */
public interface ICustomerRepository {
  Customer create(String name, String document, String email, String phoneNumber);
  Boolean saveChanges(List<Customer> customers, List<Customer> removedCustomers);
  Boolean saveAll(List<Customer> customers);
  Customer get(int id);
  List<Customer> list();
}
