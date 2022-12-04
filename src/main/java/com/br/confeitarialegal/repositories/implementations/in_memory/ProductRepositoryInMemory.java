package com.br.confeitarialegal.repositories.implementations.in_memory;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.interfaces.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryInMemory implements IProductRepository {
    List<Product> products;

    public ProductRepositoryInMemory() {
        this.products = new ArrayList<>();
    }

    @Override
    public Product create(String name, float unitaryValue, UnitaryTypes unitaryType) {
        Product product = new Product(1234, name, unitaryValue, unitaryType);
        this.products.add(product);
        return product;
    }

    public List<Product> list() {
        return this.products;
    }
}
