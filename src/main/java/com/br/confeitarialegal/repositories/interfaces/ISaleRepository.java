package com.br.confeitarialegal.repositories.interfaces;

import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;

import java.util.Date;
import java.util.List;

public interface ISaleRepository {
    Sale create(Customer customer, List<Product> products, List<Double> quantity, StatusType status, PaymentTypes paymentType, Date paymentDate, Date createdAt);
    List<Sale> list();
}
