package com.br.confeitarialegal.controller;

import com.br.confeitarialegal.entity.Product;
import com.br.confeitarialegal.repository.RepositoryMethod;
import com.br.confeitarialegal.repository.implementations.hibernate.ProductRepository;
import com.br.confeitarialegal.repository.implementations.in_memory.ProductRepositoryInMemory;
import com.br.confeitarialegal.repository.interfaces.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    IProductRepository repository;

    public ProductController(RepositoryMethod repositoryMethod) {
        if (repositoryMethod == RepositoryMethod.HIBERNATE) {
            this.repository = ProductRepository.getInstance();
        } else {
            this.repository = new ProductRepositoryInMemory();
        }
    }

    public List<Product> list() {
        List<Product> products = this.repository.list();
//        products.forEach(product -> {
//            System.out.println(product.toString());
//        });
        return products;
    }
}
