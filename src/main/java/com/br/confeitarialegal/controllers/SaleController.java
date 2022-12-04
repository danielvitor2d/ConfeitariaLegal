package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.SaleRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.SaleRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import java.util.Date;
import java.util.List;

public class SaleController {

    ISaleRepository repository;

    public SaleController(RepositoryMethod repositoryMethod) {
        if (repositoryMethod == RepositoryMethod.HIBERNATE) {
            this.repository = SaleRepository.getInstance();
        } else {
            this.repository = new SaleRepositoryInMemory();
        }
    }

    public Sale create(Customer customer, List<Product> products, StatusType status, Float totalValue, Date paymentDate, Date createdAt) {
        Sale Sale = this.repository.create(customer, products, status, totalValue, paymentDate, createdAt);
        return Sale;
    }

    public List<Sale> list() {
        List<Sale> sales = this.repository.list();
        return sales;
    }
}
