package com.br.confeitarialegal.repository.interfaces;

import com.br.confeitarialegal.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> list();
}
