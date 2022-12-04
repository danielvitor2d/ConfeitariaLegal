package com.br.confeitarialegal.tests.controller;

import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.controllers.ProductController;
import com.br.confeitarialegal.controllers.SaleController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleControllerTests {

    @Test
    public void shoud_be_able_to_create_a_sale() {
        SaleController saleController = new SaleController(RepositoryMethod.IN_MEMORY);
        CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Customer customer = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");

        Product product1 = productController.create("Coxinha", 5.0f, UnitaryTypes.UNIT);
        Product product2 = productController.create("Bolo confeitado", 25.99f, UnitaryTypes.KILOGRAM);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Date date = new Date();

        Sale sale = saleController.create(customer, products, StatusType.AWAITING_PAYMENT, 30.99f, null, date);

        Assert.assertNotNull(sale.getId());
    }

    @Test
    public void should_be_able_to_list_sales() {
        SaleController saleController = new SaleController(RepositoryMethod.IN_MEMORY);
        CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Customer customer = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");

        Product product1 = productController.create("Coxinha", 5.0f, UnitaryTypes.UNIT);
        Product product2 = productController.create("Bolo confeitado", 25.99f, UnitaryTypes.KILOGRAM);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Date date = new Date();

        Sale sale = saleController.create(customer, products, StatusType.AWAITING_PAYMENT, 30.99f, date, date);

        List<Sale> list = new ArrayList<>();
        list.add(sale);

        List<Sale> sales = saleController.list();

        Assert.assertNotNull(sales);
        Assert.assertArrayEquals(sales.toArray(), list.toArray());
    }
}
