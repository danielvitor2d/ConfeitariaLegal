package com.br.confeitarialegal.tests.controller;

import com.br.confeitarialegal.controllers.ProductController;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductControllerTests {

    @Test
    public void should_be_able_to_create_a_product() {
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Product product = productController.create("Coxinha", 5.0, UnitaryTypes.UNIT);

        Assert.assertNotNull(product.getId());
    }

    @Test
    public void should_be_able_to_list_products() {
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Product product1 = productController.create("Coxinha", 5.0, UnitaryTypes.UNIT);
        Product product2 = productController.create("Bolo confeitado", 25.99, UnitaryTypes.KILOGRAM);

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        List<Product> products = productController.list();

        Assert.assertNotNull(products);
        Assert.assertArrayEquals(list.toArray(), products.toArray());
    }
}
