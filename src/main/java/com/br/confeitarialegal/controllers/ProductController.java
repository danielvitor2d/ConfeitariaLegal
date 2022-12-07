package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.ProductRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.ProductRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.IProductRepository;

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

    public Product create(String name, double unitaryValue, UnitaryTypes unitaryType) {
        return this.repository.create(name, unitaryValue, unitaryType);
    }

    public Boolean saveChanges(List<Product> products, List<Product> removedProducts) {
        return this.repository.saveChanges(products, removedProducts);
    }

    public Product get(int id) {
        return this.repository.get(id);
    }

    public List<Product> list() {
        return new ArrayList<>(this.repository.list());
    }
}
