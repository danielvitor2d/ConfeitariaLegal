package com.br.confeitarialegal.repositories.implementations.in_memory;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleRepositoryInMemory implements ISaleRepository {
    List<Sale> sales;

    public SaleRepositoryInMemory() {
        this.sales = new ArrayList<>();
    }

    public Sale create(Customer customer, List<Product> products, StatusType status, Float totalValue, Date paymentDate, Date createdAt) {
        Sale sale = new Sale(123, customer, products, status, totalValue, paymentDate, createdAt);
        this.sales.add(sale);
        return sale;
    }

    public List<Sale> list() {
        return this.sales;
    }
}
