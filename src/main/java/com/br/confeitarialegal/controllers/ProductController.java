package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.ProductRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.ProductRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.IProductRepository;

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

    public Product create(String name, float unitaryValue, UnitaryTypes unitaryType) {
        Product product = this.repository.create(name, unitaryValue, unitaryType);
        return product;
    }

    public List<Product> list() {
        List<Product> products = this.repository.list();
        return products;
    }
}
