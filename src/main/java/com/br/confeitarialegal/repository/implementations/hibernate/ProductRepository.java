package com.br.confeitarialegal.repository.implementations.hibernate;

import com.br.confeitarialegal.entity.Product;
import com.br.confeitarialegal.entity.Product;
import com.br.confeitarialegal.hibernate.Connection;
import com.br.confeitarialegal.repository.interfaces.IProductRepository;

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
