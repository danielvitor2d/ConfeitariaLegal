package com.br.confeitarialegal.repositories.implementations.hibernate;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.hibernate.Connection;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import com.br.confeitarialegal.repositories.interfaces.ICustomerRepository;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Daniel Vitor
 */
public class CustomerRepository implements ICustomerRepository {

  private static CustomerRepository instance;

  private final EntityManager entityManager;

  private CustomerRepository() {
    this.entityManager = Connection.getInstance().create();
  }

  public static CustomerRepository getInstance() {
    if (instance == null) {
      instance = new CustomerRepository();
    }
    return instance;
  }

  @Override
  public Customer create(String name, String document, String email, String phoneNumber) {
    try {
      Customer customer = new Customer(name, document, email, phoneNumber);
      this.entityManager.getTransaction().begin();
      this.entityManager.persist(customer);
      this.entityManager.getTransaction().commit();
      return customer;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      this.entityManager.getTransaction().rollback();
    }
    return null;
  }

  @Override
  public Boolean saveChanges(List<Customer> customers, List<Customer> removedCustomers) {
    try {
      this.entityManager.getTransaction().begin();
      for (int i = 0; i < customers.size(); i++) {
        this.entityManager.merge(customers.get(i));
        if ((i % 10000) == 0) {
          this.entityManager.flush();
          this.entityManager.clear();
        }
      }

      for (Customer customer : removedCustomers) {
        this.entityManager.remove(this.entityManager.contains(customer) ? customer : this.entityManager.merge(customer));
      }
      this.entityManager.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      this.entityManager.getTransaction().rollback();
      return false;
    }
  }

  @Override
  public Boolean  saveAll(List<Customer> customers) {
    try {
      this.entityManager.getTransaction().begin();
      for (int i = 0; i < customers.size(); i++) {
        this.entityManager.merge(customers.get(i));
        if ((i % 10000) == 0) {
          this.entityManager.flush();
          this.entityManager.clear();
        }
      }
      this.entityManager.getTransaction().commit();
      return true;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      this.entityManager.getTransaction().rollback();
      return false;
    }
  }

  @Override
  public Customer get(int id) {
    Customer customer = null;
    try {
      this.entityManager.getTransaction().begin();
      customer = this.entityManager.find(Customer.class, id);
      this.entityManager.getTransaction().commit();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return customer;
  }

  @Override
  public List<Customer> list() {
    this.entityManager.getTransaction().begin();

    CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
    Root<Customer> customer = query.from(Customer.class);
    query.select(customer);
    
    TypedQuery<Customer> typedQuery = this.entityManager.createQuery(query);
    List<Customer> customers = typedQuery.getResultList();
    
    this.entityManager.getTransaction().commit();
    
    return customers;
  }

}
