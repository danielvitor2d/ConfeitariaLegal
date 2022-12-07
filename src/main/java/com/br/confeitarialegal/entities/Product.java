package com.br.confeitarialegal.entities;

import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "library_products")
@Access(AccessType.PROPERTY)
public class Product {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
//    private SimpleListProperty<ProductsSales> productsSales = new SimpleListProperty<>();
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize = 1)
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

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    public List<ProductsSales> getProductsSales() {
//        return productsSales.getValue();
//    }
//
//    public void setProductsSales(List<ProductsSales> productsSales) {
//        this.productsSales.setValue(FXCollections.observableArrayList(productsSales));
//    }

    @Column(name = "name")
    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public SimpleStringProperty nameProperty() {
        return this.name;
    }

    @Column(name = "unitary_value")
    public Double getUnitaryValue() {
        return unitaryValue.getValue();
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue.setValue(unitaryValue);
    }

    public SimpleDoubleProperty unitaryValueProperty() { return this.unitaryValue; }

    @Column(name = "unitary_type")
    public UnitaryTypes getUnitaryType() {
        return unitaryType.getValue();
    }

    public void setUnitaryType(UnitaryTypes unitaryType) {
        this.unitaryType.setValue(unitaryType);
    }

    public SimpleObjectProperty<UnitaryTypes> unitaryTypeProperty() { return this.unitaryType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!Objects.equals(id.getValue(), product.id.getValue())) return false;
//        if (!Objects.equals(productsSales.getValue(), product.productsSales.getValue()))
//            return false;
        if (!Objects.equals(name.getValue(), product.name.getValue())) return false;
        if (!Objects.equals(unitaryValue.getValue(), product.unitaryValue.getValue()))
            return false;
        return Objects.equals(unitaryType.getValue(), product.unitaryType.getValue());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (productsSales != null ? productsSales.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unitaryValue != null ? unitaryValue.hashCode() : 0);
        result = 31 * result + (unitaryType != null ? unitaryType.hashCode() : 0);
        return result;
    }

    public Product createClone() {
        Product product = new Product();
        product.setId(this.getId());
//        product.setProductsSales(List.copyOf(this.getProductsSales()));
        product.setUnitaryType(this.getUnitaryType());
        product.setName(this.getName());
        product.setUnitaryValue(this.getUnitaryValue());
        return product;
    }

    public void clone(Product product) {
        this.setId(product.getId());
        this.setUnitaryType(product.getUnitaryType());
        this.setUnitaryValue(product.getUnitaryValue());
        this.setName(product.getName());
//        this.setProductsSales(List.copyOf(product.getProductsSales()));
    }
}
