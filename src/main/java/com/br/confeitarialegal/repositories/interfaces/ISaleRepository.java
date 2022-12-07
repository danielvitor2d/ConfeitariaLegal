package com.br.confeitarialegal.repositories.interfaces;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;

import java.time.LocalDate;
import java.util.List;

public interface ISaleRepository {
    Sale create(Customer customer, List<Product> products, List<Double> quantity, StatusType status, PaymentTypes paymentType, LocalDate paymentDate, LocalDate createdAt);
    Boolean saveChanges(List<Sale> sales, List<Sale> removedSales);
    List<Sale> list();
}
