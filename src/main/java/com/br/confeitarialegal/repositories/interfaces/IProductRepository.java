package com.br.confeitarialegal.repositories.interfaces;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;

import java.util.List;

public interface IProductRepository {
    Product create(String name, double unitaryValue, UnitaryTypes unitaryType);
    Product get(int id);
    List<Product> list();
}
