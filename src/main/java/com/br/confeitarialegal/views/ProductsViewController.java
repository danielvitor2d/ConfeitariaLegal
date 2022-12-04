package com.br.confeitarialegal.views;

import com.br.confeitarialegal.controllers.ProductController;
import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.utils.FormatData;
import com.br.confeitarialegal.views.utils.ManageScreens;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author danielvitor
 */
public class ProductsViewController implements Initializable {

  @FXML
  TableView<Product> tableViewProducts;
  
  @FXML
  TableColumn<Product, String> tableColumnId;
  
  @FXML
  TableColumn<Product, String> tableColumnProduct;
  
  @FXML
  TableColumn<Product, String> tableColumnUnitaryValue;
  
  @FXML
  TableColumn<Product, String> tableColumnUnitaryType;
  
  @FXML
  AnchorPane anchorPaneProducts;
  
  @FXML
  HBox saleButton;
  
  @FXML
  HBox customerButton;
  
  @FXML
  HBox productButton;
  
  @FXML
  HBox logoutButton;
  
  ProductController productController;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.productController = new ProductController(RepositoryMethod.HIBERNATE);

    initializeTableView();
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
    this.tableColumnProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.tableColumnUnitaryValue.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toCurrencyBRL(cellData.getValue().getUnitaryValue())));
    this.tableColumnUnitaryType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnitaryType().getType()));

    Task<List<Product>> task = new Task<>() {
      @Override
      protected List<Product> call() {
        return productController.list();
      }

      @Override
      protected void succeeded() {
        tableViewProducts.getItems().clear();
        tableViewProducts.getItems().addAll(this.getValue());
      }
    };

    new Thread(task).start();
  }

  public void switchToSales() {
    ManageScreens.switchToSales();
  }

  public void switchToCustomers() {
    ManageScreens.switchToCustomers();
  }

  public void switchToProducts() {
    ManageScreens.switchToProducts();
  }

  public void logout() {
    ManageScreens.switchToLogin();
  }
}
