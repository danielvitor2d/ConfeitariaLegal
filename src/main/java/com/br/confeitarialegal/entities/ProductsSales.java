package com.br.confeitarialegal.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.*;

@Entity(name = "library_products_sales")
@Access(AccessType.PROPERTY)
public class ProductsSales {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleObjectProperty<Sale> sale;
    private SimpleObjectProperty<Product> product;
    private SimpleDoubleProperty quantity;
    private SimpleDoubleProperty totalValue;

    @Override
    public String toString() {
        return "ProductsSales{" +
                "sale=" + sale.getValue() +
                ", product=" + product.getValue() +
                ", quantity=" + quantity.getValue() +
                ", totalValue=" + totalValue.getValue() +
                '}';
    }

    public ProductsSales() {
        this.sale = new SimpleObjectProperty<>();
        this.product = new SimpleObjectProperty<>();
        this.quantity = new SimpleDoubleProperty();
        this.totalValue = new SimpleDoubleProperty();
    }

    public ProductsSales(Product product, Double quantity, Double totalValue) {
        this.sale = new SimpleObjectProperty<>();
        this.product = new SimpleObjectProperty<>(product);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.totalValue = new SimpleDoubleProperty(totalValue);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id.setValue(id);
    }

    public SimpleIntegerProperty idProperty() {
        return this.id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id")
    public Sale getSale() {
        return sale.getValue();
    }

    public void setSale(Sale sale) {
        this.sale.setValue(sale);
    }

    public SimpleObjectProperty saleProperty() {
        return this.sale;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product.getValue();
    }

    public void setProduct(Product product) {
        this.product.setValue(product);
    }

    public SimpleObjectProperty productProperty() {
        return this.product;
    }


    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity.getValue();
    }

    public void setQuantity(Double quantity) {
        this.quantity.setValue(quantity);
    }

    public SimpleDoubleProperty quantityProperty() {
        return this.quantity;
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
}
