package com.br.confeitarialegal.repository.implementations.hibernate;

import com.br.confeitarialegal.entity.Customer;
import com.br.confeitarialegal.hibernate.Connection;
import java.util.List;
import javax.persistence.EntityManager;
import com.br.confeitarialegal.repository.interfaces.ICustomerRepository;
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
  public Customer create(String name, String document, String email, String phonenumber) {
    try {
      Customer customer = new Customer(name, document, email, phonenumber);
      this.entityManager.getTransaction().begin();
      this.entityManager.persist(customer);
      return customer;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      this.entityManager.getTransaction().rollback();
    } finally {
      this.entityManager.getTransaction().commit();
    }
    return null;
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
