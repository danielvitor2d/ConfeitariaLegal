package com.br.confeitarialegal.repositories.implementations.in_memory;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.interfaces.IProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryInMemory implements IProductRepository {
    List<Product> products;

    public ProductRepositoryInMemory() {
        this.products = new ArrayList<>();
    }

    @Override
    public Product create(String name, double unitaryValue, UnitaryTypes unitaryType) {
        Product product = new Product(1234, name, unitaryValue, unitaryType);
        this.products.add(product);
        return product;
    }

    @Override
    public Product get(int id) {
        Optional<Product> result = this.products.stream().filter(product -> product.getId() == id).findFirst();
        return result.orElse(null);
    }

    public List<Product> list() {
        return this.products;
    }
}
