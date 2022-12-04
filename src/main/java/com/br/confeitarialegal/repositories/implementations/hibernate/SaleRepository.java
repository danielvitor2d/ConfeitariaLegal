package com.br.confeitarialegal.repositories.implementations.hibernate;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class SaleRepository implements ISaleRepository {
    private static SaleRepository instance;

    private final EntityManager entityManager;

    private SaleRepository() {
        this.entityManager = Connection.getInstance().create();
    }

    public static SaleRepository getInstance() {
        if (instance == null) {
            instance = new SaleRepository();
        }
        return instance;
    }

    @Override
    public Sale create(Customer customer, List<Product> products, StatusType status, Float totalValue, Date paymentDate, Date createdAt) {
        try {
            Sale sale = new Sale(customer, products, status, totalValue, paymentDate, createdAt);
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(sale);
            return sale;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.getTransaction().commit();
        }
        return null;
    }

    @Override
    public List<Sale> list() {
        this.entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Sale> query = criteriaBuilder.createQuery(Sale.class);
        Root<Sale> sale = query.from(Sale.class);
        query.select(sale);

        TypedQuery<Sale> typedQuery = this.entityManager.createQuery(query);
        List<Sale> sales = typedQuery.getResultList();

        this.entityManager.getTransaction().commit();

        return sales;
    }
}