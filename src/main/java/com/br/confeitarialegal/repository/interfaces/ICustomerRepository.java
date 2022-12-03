package com.br.confeitarialegal.repository.interfaces;

import java.util.List;

import com.br.confeitarialegal.entity.Customer;

/**
 *
 * @author Daniel Vitor
 */
public interface ICustomerRepository {
  Customer create(String name, String document, String email, String phonenumber);
  List<Customer> list();
}
