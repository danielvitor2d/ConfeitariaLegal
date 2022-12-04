package com.br.confeitarialegal.entities;


import com.br.confeitarialegal.entities.enums.UnitaryTypes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "library_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductsSales> productsSales = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "unitary_value")
    private Double unitaryValue;

    @Column(name = "unitary_type")
    private UnitaryTypes unitaryType;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitaryValue=" + unitaryValue +
                ", unitaryType=" + unitaryType +
                '}';
    }

    public Product() {
    }

    public Product(Integer id, String name, Double unitaryValue, UnitaryTypes unitaryType) {
        this.id = id;
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.unitaryType = unitaryType;
    }

    public Product(String name, Double unitaryValue, UnitaryTypes unitaryType) {
        this.name = name;
        this.unitaryValue = unitaryValue;
        this.unitaryType = unitaryType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<ProductsSales> getProductsSales() {
        return productsSales;
    }

    public void setProductsSales(Set<ProductsSales> productsSales) {
        this.productsSales = productsSales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public UnitaryTypes getUnitaryType() {
        return unitaryType;
    }

    public void setUnitaryType(UnitaryTypes unitaryType) {
        this.unitaryType = unitaryType;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(id, product.id) && Objects.equals(productsSales, product.productsSales) && Objects.equals(name, product.name) && Objects.equals(unitaryValue, product.unitaryValue) && unitaryType == product.unitaryType;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, productsSales, name, unitaryValue, unitaryType);
//    }
}
