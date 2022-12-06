package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "library_sales")
@Access(AccessType.PROPERTY)
public class Sale {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleObjectProperty<Customer> customer;
    private SimpleListProperty<ProductsSales> productsSales = new SimpleListProperty<>();
    private SimpleObjectProperty<StatusType> status;
    private SimpleObjectProperty<PaymentTypes> paymentType;
    private SimpleDoubleProperty totalValue;
    private SimpleObjectProperty<Date> paymentDate;
    private SimpleObjectProperty<Date> createdAt;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id.getValue() +
                ", customer=" + customer.getValue() +
                ", status=" + status.getValue() +
                ", paymentType=" + paymentType.getValue() +
                ", totalValue=" + totalValue.getValue() +
                ", paymentDate=" + paymentDate.getValue() +
                ", createdAt=" + createdAt.getValue() +
                '}';
    }

    public Sale() {
        this.productsSales = new SimpleListProperty<>();

        this.customer = new SimpleObjectProperty<>();
        this.status = new SimpleObjectProperty<>();
        this.paymentType = new SimpleObjectProperty<>();
        this.totalValue = new SimpleDoubleProperty();
        this.paymentDate = new SimpleObjectProperty<>();
        this.createdAt = new SimpleObjectProperty<>();
    }

    public Sale(Customer customer, List<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, Date paymentDate, Date createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = new SimpleListProperty<>(FXCollections.observableArrayList(productsSales));

        this.customer = new SimpleObjectProperty<>(customer);
        this.status = new SimpleObjectProperty<>(status);
        this.paymentType = new SimpleObjectProperty<>(paymentType);
        this.totalValue = new SimpleDoubleProperty(totalValue);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    public Sale(Integer id, Customer customer, List<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, Date paymentDate, Date createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = new SimpleListProperty<>(FXCollections.observableArrayList(productsSales));

        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<>(customer);
        this.status = new SimpleObjectProperty<>(status);
        this.paymentType = new SimpleObjectProperty<>(paymentType);
        this.totalValue = new SimpleDoubleProperty(totalValue);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id.getValue();
    }

    public void setId(Integer id) {
        this.id.setValue(id);
    }

    public SimpleIntegerProperty idProperty() {
        return this.id;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer.getValue();
    }

    public void setCustomer(Customer customer) {
        this.customer.setValue(customer);
    }

    public SimpleObjectProperty<Customer> customerProperty() {
        return this.customer;
    }


    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    public List<ProductsSales> getProductsSales() {
        return productsSales.getValue();
    }

    public void setProductsSales(List<ProductsSales> productsSales) {
        this.productsSales.setValue(FXCollections.observableArrayList(productsSales));
    }

    public SimpleListProperty<ProductsSales> productsSalesProperty() {
        return this.productsSales;
    }

    @Column(name = "status")
    public StatusType getStatus() {
        return status.getValue();
    }

    public void setStatus(StatusType status) {
        this.status.setValue(status);
    }

    public SimpleObjectProperty<StatusType> statusProperty() {
        return this.status;
    }

    @Column(name = "payment_type")
    public PaymentTypes getPaymentType() {
        return paymentType.getValue();
    }

    public void setPaymentType(PaymentTypes paymentType) {
        this.paymentType.setValue(paymentType);
    }

    public SimpleObjectProperty<PaymentTypes> paymentTypeProperty() {
        return this.paymentType;
    }

    @Column(name = "total_value")
    public Double getTotalValue() {
        return totalValue.getValue();
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue.setValue(totalValue);
    }

    public SimpleDoubleProperty totalValueProperty() {
        return this.totalValue;
    }

    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate.getValue();
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate.setValue(paymentDate);
    }

    public SimpleObjectProperty<Date> paymentDateProperty() {
        return this.paymentDate;
    }

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt.getValue();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt.setValue(createdAt);
    }

    public SimpleObjectProperty<Date> createdAtProperty() {
        return this.createdAt;
    }
}
