package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "library_sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<ProductsSales> productsSales;

    @Column(name = "status")
    private StatusType status;

    @Column(name = "payment_type")
    private PaymentTypes paymentType;

    @Column(name = "total_value")
    private Double totalValue;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customer=" + customer +
                ", status=" + status +
                ", paymentType=" + paymentType +
                ", totalValue=" + totalValue +
                ", paymentDate=" + paymentDate +
                ", createdAt=" + createdAt +
                '}';
    }

    public Sale() {
        this.productsSales = new HashSet<>();
    }

    public Sale(Customer customer, Set<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, Date paymentDate, Date createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = productsSales;

        this.customer = customer;
        this.status = status;
        this.paymentType = paymentType;
        this.totalValue = totalValue;
        this.paymentDate = paymentDate;
        this.createdAt = createdAt;
    }

    public Sale(Integer id, Customer customer, Set<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, Date paymentDate, Date createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = productsSales;

        this.id = id;
        this.customer = customer;
        this.status = status;
        this.paymentType = paymentType;
        this.totalValue = totalValue;
        this.paymentDate = paymentDate;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductsSales> getProductsSales() {
        return productsSales;
    }

    public void setProductsSales(Set<ProductsSales> productsSales) {
        this.productsSales = productsSales;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sale sale = (Sale) o;
//        return Objects.equals(id, sale.id) && Objects.equals(customer, sale.customer) && Objects.equals(productsSales, sale.productsSales) && status == sale.status && paymentType == sale.paymentType && Objects.equals(totalValue, sale.totalValue) && Objects.equals(paymentDate, sale.paymentDate) && Objects.equals(createdAt, sale.createdAt);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, customer, productsSales, status, paymentType, totalValue, paymentDate, createdAt);
//    }
}
