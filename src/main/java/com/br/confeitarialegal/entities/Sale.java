package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(name = "library_sales")
@Access(AccessType.PROPERTY)
public class Sale {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleObjectProperty<Customer> customer;
    private SimpleListProperty<ProductsSales> productsSales;
    private SimpleObjectProperty<StatusType> status;
    private SimpleObjectProperty<PaymentTypes> paymentType;
    private SimpleDoubleProperty totalValue;
    private SimpleObjectProperty<LocalDate> paymentDate;
    private SimpleObjectProperty<LocalDate> createdAt;

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
        this.productsSales = new SimpleListProperty<>(FXCollections.observableArrayList());

        this.customer = new SimpleObjectProperty<>(new Customer());
        this.status = new SimpleObjectProperty<>();
        this.paymentType = new SimpleObjectProperty<>();
        this.totalValue = new SimpleDoubleProperty();
        this.paymentDate = new SimpleObjectProperty<>();
        this.createdAt = new SimpleObjectProperty<>();
    }

    public Sale(Customer customer, List<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, LocalDate paymentDate, LocalDate createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = new SimpleListProperty<>(FXCollections.observableArrayList(List.copyOf(productsSales)));

        this.customer = new SimpleObjectProperty<>(customer);
        this.status = new SimpleObjectProperty<>(status);
        this.paymentType = new SimpleObjectProperty<>(paymentType);
        this.totalValue = new SimpleDoubleProperty(totalValue);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    public Sale(Integer id, Customer customer, List<ProductsSales> productsSales, StatusType status, PaymentTypes paymentType, Double totalValue, LocalDate paymentDate, LocalDate createdAt) {
        for (ProductsSales productsSale : productsSales) {
            productsSale.setSale(this);
        }
        this.productsSales = new SimpleListProperty<>(FXCollections.observableArrayList(List.copyOf(productsSales)));

        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<>(customer);
        this.status = new SimpleObjectProperty<>(status);
        this.paymentType = new SimpleObjectProperty<>(paymentType);
        this.totalValue = new SimpleDoubleProperty(totalValue);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_generator")
    @SequenceGenerator(name="sale_generator", sequenceName = "sale_seq", allocationSize = 1)
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

    @Temporal(TemporalType.DATE)
    @Column(name = "payment_date")
    public LocalDate getPaymentDate() {
        return paymentDate.getValue();
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate.setValue(paymentDate);
    }

    public SimpleObjectProperty<LocalDate> paymentDateProperty() {
        return this.paymentDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    public LocalDate getCreatedAt() {
        return createdAt.getValue();
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt.setValue(createdAt);
    }

    public SimpleObjectProperty<LocalDate> createdAtProperty() {
        return this.createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        if (!Objects.equals(id.getValue(), sale.id.getValue())) return false;
        if (!Objects.equals(customer.getValue(), sale.customer.getValue())) return false;
        if (!Objects.equals(productsSales.getValue(), sale.productsSales.getValue()))
            return false;
        if (!Objects.equals(status.getValue(), sale.status.getValue())) return false;
        if (!Objects.equals(paymentType.getValue(), sale.paymentType.getValue())) return false;
        if (!Objects.equals(totalValue.getValue(), sale.totalValue.getValue())) return false;
        if (!Objects.equals(paymentDate.getValue(), sale.paymentDate.getValue())) return false;
        return Objects.equals(createdAt.getValue(), sale.createdAt.getValue());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (productsSales != null ? productsSales.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (totalValue != null ? totalValue.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    public Sale createClone() {
        Sale sale = new Sale();
        sale.setId(this.getId());
        sale.setCreatedAt(this.getCreatedAt());
        sale.setProductsSales(List.copyOf(this.getProductsSales()));
        sale.setCustomer(this.getCustomer().createClone());
        sale.setStatus(this.getStatus());
        sale.setPaymentDate(this.getPaymentDate());
        sale.setPaymentType(this.getPaymentType());
        sale.setTotalValue(this.getTotalValue());
        assert(this != sale);
        return sale;
    }

    public void clone(Sale sale) {
        this.setId(sale.getId());
        this.setCreatedAt(sale.getCreatedAt());
        this.setProductsSales(List.copyOf(sale.getProductsSales()));
        this.setCustomer(sale.getCustomer().createClone());
        this.setStatus(sale.getStatus());
        this.setPaymentDate(sale.getPaymentDate());
        this.setPaymentType(sale.getPaymentType());
        this.setTotalValue(sale.getTotalValue());
    }
}
