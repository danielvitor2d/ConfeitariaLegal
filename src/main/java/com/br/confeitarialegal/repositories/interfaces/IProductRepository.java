package com.br.confeitarialegal.repositories.interfaces;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;

import java.util.List;

public interface IProductRepository {
    Product create(String name, float unitaryValue, UnitaryTypes unitaryType);
    List<Product> list();
}
