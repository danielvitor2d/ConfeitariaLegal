package com.br.confeitarialegal.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "library_products_sales")
public class ProductsSales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "total_value")
    private Double totalValue;

    @Override
    public String toString() {
        return "ProductsSales{" +
                "sale=" + sale +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalValue=" + totalValue +
                '}';
    }

    public ProductsSales() {
    }

    public ProductsSales(Product product, Double quantity, Double totalValue) {
        this.product = product;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductsSales)) return false;
//        ProductsSales that = (ProductsSales) o;
//        return id == that.id && Objects.equals(sale, that.sale) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity) && Objects.equals(totalValue, that.totalValue);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, sale, product, quantity, totalValue);
//    }
}
