package com.br.confeitarialegal.repositories.implementations.hibernate;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repositories.interfaces.IProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static ProductRepository instance;

    private final EntityManager entityManager;

    private ProductRepository() {
        this.entityManager = Connection.getInstance().create();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    @Override
    public Product create(String name, double unitaryValue, UnitaryTypes unitaryType) {
        try {
            Product product = new Product(name, unitaryValue, unitaryType);
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(product);
            this.entityManager.getTransaction().commit();
            return product;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.entityManager.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Boolean saveChanges(List<Product> products, List<Product> removedProducts) {
        try {
            this.entityManager.getTransaction().begin();
            for (int i = 0; i < products.size(); i++) {
                this.entityManager.merge(products.get(i));
                if ((i % 10000) == 0) {
                    this.entityManager.flush();
                    this.entityManager.clear();
                }
            }

            for (Product product : removedProducts) {
                this.entityManager.remove(this.entityManager.contains(product) ? product : this.entityManager.merge(product));
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
    public Product get(int id) {
        Product product = null;
        try {
            this.entityManager.getTransaction().begin();
            product = this.entityManager.find(Product.class, id);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> list() {
        this.entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        query.select(product);

        TypedQuery<Product> typedQuery = this.entityManager.createQuery(query);
        List<Product> products = typedQuery.getResultList();

        this.entityManager.getTransaction().commit();

        return products;
    }
}
