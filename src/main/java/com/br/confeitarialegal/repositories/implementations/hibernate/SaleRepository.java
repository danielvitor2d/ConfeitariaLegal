package com.br.confeitarialegal.repositories.implementations.hibernate;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.ProductsSales;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;

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
    public Sale create(Customer customer, List<Product> products, List<Double> quantity, StatusType status, PaymentTypes paymentType, LocalDate paymentDate, LocalDate createdAt) {
        try {
            List<ProductsSales> productsSales = new ArrayList<>();

            double totalValue = 0.0;

            for (int idx = 0; idx < products.size(); idx++) {
                Product productToSave = products.get(idx);
                Double quantityToSave = quantity.get(idx);
                double totalValueToSave = quantityToSave * productToSave.getUnitaryValue();
                totalValue += totalValueToSave;
                productsSales.add(new ProductsSales(productToSave, quantityToSave, totalValueToSave));
            }

            Sale sale = new Sale(customer, productsSales, status, paymentType, totalValue, paymentDate, createdAt);

            this.entityManager.getTransaction().begin();

            this.entityManager.persist(sale);

            this.entityManager.getTransaction().commit();

            return sale;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.entityManager.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Boolean saveChanges(List<Sale> sales, List<Sale> removedSales) {
        try {
            this.entityManager.getTransaction().begin();
            for (int i = 0; i < sales.size(); i++) {
                this.entityManager.merge(sales.get(i));
                if ((i % 10000) == 0) {
                    this.entityManager.flush();
                    this.entityManager.clear();
                }
            }

            for (Sale sale : removedSales) {
                this.entityManager.remove(this.entityManager.contains(sale) ? sale : this.entityManager.merge(sale));
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
