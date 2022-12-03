package com.br.confeitarialegal.tests.controller;

import org.junit.Test;
import java.util.List;
import org.junit.Assert;

import com.br.confeitarialegal.entity.Customer;
import com.br.confeitarialegal.controller.CustomerController;
import com.br.confeitarialegal.repository.RepositoryMethod;

/**
 *
 * @author Daniel Vitor
 */
public class CustomerControllerTests {
  
  @Test
  public void shoud_be_able_to_create_a_customer() {
    CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);

    // name, cpf, phonenumber
    Customer customer = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");
    
    Assert.assertNotNull(customer.getId());
  }
  
  @Test
  public void should_be_able_to_list_customers() {
    CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);
    
    List<Customer> customers = customerController.list();
    
    Assert.assertNotNull(customers);
  }
  
}
