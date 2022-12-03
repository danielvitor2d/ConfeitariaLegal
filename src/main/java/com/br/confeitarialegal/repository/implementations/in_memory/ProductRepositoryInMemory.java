package com.br.confeitarialegal.repository.implementations.in_memory;

import com.br.confeitarialegal.entity.Product;
import com.br.confeitarialegal.repository.interfaces.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryInMemory implements IProductRepository {
    List<Product> products;

    public ProductRepositoryInMemory() {
        this.products = new ArrayList<>();
    }

    public List<Product> list() {
        return this.products;
    }
}
