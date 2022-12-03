package com.br.confeitarialegal.entity;


import com.br.confeitarialegal.entity.enums.UnitaryTypes;

import javax.persistence.*;

@Entity(name = "library_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "unitary_value")
    private Float unitaryValue;

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

    public Product(String name, Float unitaryValue, UnitaryTypes unitaryType) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(Float unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public UnitaryTypes getUnitaryType() {
        return unitaryType;
    }

    public void setUnitaryType(UnitaryTypes unitaryType) {
        this.unitaryType = unitaryType;
    }
}
