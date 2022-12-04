package com.br.confeitarialegal.views;

import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.views.utils.ManageScreens;
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
public class CustomersViewController implements Initializable {

  @FXML
  TableView<Customer> tableViewCustomers;
  
  @FXML
  TableColumn<Customer, Integer> tableColumnId;
  
  @FXML
  TableColumn<Customer, String> tableColumnCustomer;
  
  @FXML
  TableColumn<Customer, String> tableColumnDocument;
  
  @FXML
  TableColumn<Customer, String> tableColumnEmail;
  
  @FXML
  AnchorPane anchorPaneCustomers;
  
  @FXML
  HBox saleButton;
  
  @FXML
  HBox customerButton;
  
  @FXML
  HBox productButton;
  
  @FXML
  HBox logoutButton;

  CustomerController customerController;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.customerController = new CustomerController(RepositoryMethod.HIBERNATE);

    initializeTableView();
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.tableColumnDocument.setCellValueFactory(new PropertyValueFactory<>("document"));
    this.tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    Task<List<Customer>> task = new Task<>() {
      @Override
      protected List<Customer> call() {
        return customerController.list();
      }

      @Override
      protected void succeeded() {
        tableViewCustomers.getItems().clear();
        tableViewCustomers.getItems().addAll(this.getValue());
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
