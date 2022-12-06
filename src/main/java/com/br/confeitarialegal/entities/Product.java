package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import javax.persistence.*;
import java.util.List;

@Entity(name = "library_products")
@Access(AccessType.PROPERTY)
public class Product {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleListProperty<ProductsSales> productsSales = new SimpleListProperty<>();
    private SimpleStringProperty name;
    private SimpleDoubleProperty unitaryValue;
    private SimpleObjectProperty<UnitaryTypes> unitaryType;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id.getValue() +
                ", name='" + name.getValue() + '\'' +
                ", unitaryValue=" + unitaryValue.getValue() +
                ", unitaryType=" + unitaryType.getValue() +
                '}';
    }

    public Product() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty("");
        this.unitaryValue = new SimpleDoubleProperty();
        this.unitaryType = new SimpleObjectProperty<>(UnitaryTypes.UNIT);
    }

    public Product(Integer id, String name, Double unitaryValue, UnitaryTypes unitaryType) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.unitaryValue = new SimpleDoubleProperty(unitaryValue);
        this.unitaryType = new SimpleObjectProperty<>(unitaryType);
    }

    public Product(String name, Double unitaryValue, UnitaryTypes unitaryType) {
        this.name = new SimpleStringProperty(name);
        this.unitaryValue = new SimpleDoubleProperty(unitaryValue);
        this.unitaryType = new SimpleObjectProperty<>(unitaryType);
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductsSales> getProductsSales() {
        return productsSales.getValue();
    }

    public void setProductsSales(List<ProductsSales> productsSales) {
        this.productsSales.setValue(FXCollections.observableArrayList(productsSales));
    }

    @Column(name = "name")
    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    @Column(name = "unitary_value")
    public Double getUnitaryValue() {
        return unitaryValue.getValue();
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue.setValue(unitaryValue);
    }

    @Column(name = "unitary_type")
    public UnitaryTypes getUnitaryType() {
        return unitaryType.getValue();
    }

    public void setUnitaryType(UnitaryTypes unitaryType) {
        this.unitaryType.setValue(unitaryType);
    }
}
