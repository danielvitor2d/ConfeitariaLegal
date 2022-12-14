package com.br.confeitarialegal.controllers;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.repositories.implementations.hibernate.SaleRepository;
import com.br.confeitarialegal.repositories.implementations.in_memory.SaleRepositoryInMemory;
import com.br.confeitarialegal.repositories.interfaces.ISaleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Sale create(Customer customer, List<Product> products, List<Double> quantity, StatusType status, PaymentTypes paymentType, LocalDate paymentDate, LocalDate createdAt) {
        return this.repository.create(customer, products, quantity, status, paymentType, paymentDate, createdAt);
    }

    public Boolean saveChanges(List<Sale> sales, List<Sale> removedSales) {
        return this.repository.saveChanges(sales, removedSales);
    }

    public List<Sale> list() {
        return new ArrayList<>(this.repository.list());
    }
}
