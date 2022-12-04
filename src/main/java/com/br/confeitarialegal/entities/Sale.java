package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.StatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "library_sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "products_sales",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id")
    )
    private List<Product> products;

    @Column(name = "status")
    private StatusType status;

    @Column(name = "total_value")
    private Float totalValue;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "created_at")
    private Date createdAt;

    public Sale() { }

    public Sale(Customer customer, List<Product> products, StatusType status, Float totalValue, Date paymentDate, Date createdAt) {
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.totalValue = totalValue;
        this.paymentDate = paymentDate;
        this.createdAt = createdAt;
    }

    public Sale(Integer id, Customer customer, List<Product> products, StatusType status, Float totalValue, Date paymentDate, Date createdAt) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.totalValue = totalValue;
        this.paymentDate = paymentDate;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", status=" + status +
                ", totalValue=" + totalValue +
                ", paymentDate=" + paymentDate +
                ", createdAt=" + createdAt +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
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
}
