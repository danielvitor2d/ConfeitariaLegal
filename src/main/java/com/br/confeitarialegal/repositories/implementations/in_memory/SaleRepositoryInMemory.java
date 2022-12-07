package com.br.confeitarialegal.repositories.implementations.in_memory;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.ProductsSales;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import java.time.LocalDate;
import java.util.*;

public class SaleRepositoryInMemory implements ISaleRepository {
    List<Sale> sales;

    public SaleRepositoryInMemory() {
        this.sales = new ArrayList<>();
    }

    public Sale create(Customer customer, List<Product> products, List<Double> quantity, StatusType status, PaymentTypes paymentType, LocalDate paymentDate, LocalDate createdAt) {
        List<ProductsSales> productsSales = new ArrayList<>();

        double totalValue = 0.0;

        for (int idx = 0; idx < products.size(); idx++) {
            Product productToSave = products.get(idx);
            Double quantityToSave = quantity.get(idx);
            Double totalValueToSave = quantityToSave * productToSave.getUnitaryValue();
            totalValue += totalValueToSave;
            productsSales.add(new ProductsSales(productToSave, quantityToSave, totalValueToSave));
        }

        Sale sale = new Sale(123, customer, productsSales, status, paymentType, totalValue, paymentDate, createdAt);
        this.sales.add(sale);

        return sale;
    }

    @Override
    public Boolean saveChanges(List<Sale> sales, List<Sale> removedSales) {
        return null;
    }

    public List<Sale> list() {
        return this.sales;
    }
}
