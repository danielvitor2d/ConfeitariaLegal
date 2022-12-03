package com.br.confeitarialegal.tests.controller;

import com.br.confeitarialegal.controller.ProductController;
import com.br.confeitarialegal.entity.Product;
import com.br.confeitarialegal.repository.RepositoryMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductControllerTests {
    @Test
    public void should_be_able_to_list_products() {
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        List<Product> products = productController.list();

        Assert.assertNotNull(products);
    }
}
