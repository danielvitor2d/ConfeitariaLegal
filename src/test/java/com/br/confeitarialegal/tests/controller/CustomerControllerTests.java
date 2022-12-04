package com.br.confeitarialegal.tests.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.repositories.RepositoryMethod;

/**
 *
 * @author Daniel Vitor
 */
public class CustomerControllerTests {
  
  @Test
  public void should_be_able_to_create_a_customer() {
    CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);

    Customer customer = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");
    
    Assert.assertNotNull(customer.getId());
  }
  
  @Test
  public void should_be_able_to_list_customers() {
    CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);

    Customer customer1 = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");
    Customer customer2 = customerController.create("Gustavo", "364.634.234-63", "gustavo@gmail.com", "88997451355");

    List<Customer> list = new ArrayList<>();
    list.add(customer1);
    list.add(customer2);

    List<Customer> customers = customerController.list();
    
    Assert.assertNotNull(customers);
    Assert.assertArrayEquals(list.toArray(), customers.toArray());
  }
  
}
