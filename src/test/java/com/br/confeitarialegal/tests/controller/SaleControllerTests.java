package com.br.confeitarialegal.tests.controller;

import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.controllers.ProductController;
import com.br.confeitarialegal.controllers.SaleController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.List.*;

public class SaleControllerTests {

    @Test
    public void should_be_able_to_create_a_sale() {
        SaleController saleController = new SaleController(RepositoryMethod.IN_MEMORY);
        CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Customer customer = customerController.create("Daniel", "048.809.450-05", "daniel@gmail.com", "88996134386");

        Product product1 = productController.create("Coxinha", 5.0f, UnitaryTypes.UNIT);
        Product product2 = productController.create("Bolo confeitado", 25.99f, UnitaryTypes.KILOGRAM);

        List<Double> quantity = new ArrayList<>(of(2.0, 1.0));

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        LocalDate date = LocalDate.now();

        Sale sale = saleController.create(customer, products, quantity, StatusType.AWAITING_PAYMENT, PaymentTypes.CREDIT_CARD, null, date);

        Assert.assertNotNull(sale.getId());
    }

    @Test
    public void should_be_able_to_list_sales() {
        SaleController saleController = new SaleController(RepositoryMethod.IN_MEMORY);
        CustomerController customerController = new CustomerController(RepositoryMethod.IN_MEMORY);
        ProductController productController = new ProductController(RepositoryMethod.IN_MEMORY);

        Customer customer1 = customerController.create("Kaio", "048.809.450-05", "kaio@gmail.com", "85926485726");
        Customer customer2 = customerController.create("Wallace", "536.723.246-63", "wallace@gmail.com", "88927384612");

        Product product1 = productController.create("Salada", 7.32, UnitaryTypes.UNIT);
        Product product2 = productController.create("Pote de Nutella", 32.85, UnitaryTypes.LITER);
        Product product3 = productController.create("Açaí", 4.99, UnitaryTypes.GRAM);

        List<Double> quantity1 = new ArrayList<>(of(2.0, 1.0));
        List<Double> quantity2 = new ArrayList<>(of(1.5, 500.0));

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        List<Product> products2 = new ArrayList<>();
        products2.add(product2);
        products2.add(product3);

        LocalDate date = LocalDate.now();

        Sale sale1 = saleController.create(customer1, products, quantity1, StatusType.DONE, PaymentTypes.PIX, date, date);
        Sale sale2 = saleController.create(customer2, products2, quantity2, StatusType.AWAITING_PAYMENT, PaymentTypes.DEFAULT, null, date);

        List<Sale> list = new ArrayList<>();
        list.add(sale1);
        list.add(sale2);

        List<Sale> sales = saleController.list();

        sales.forEach(sale -> System.out.println(sale.toString()));

        Assert.assertNotNull(sales);
        Assert.assertArrayEquals(sales.toArray(), list.toArray());
    }
}
