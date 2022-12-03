package com.br.confeitarialegal.view;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.controller.CustomerController;
import com.br.confeitarialegal.entity.Customer;
import com.br.confeitarialegal.repository.RepositoryMethod;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author danielvitor
 */
public class DashboardController implements Initializable {

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
  
//  @FXML
//  TableColumn<Customer, String> tableColumnActions;
  
  @FXML
  AnchorPane anchorPaneCustomers;
  
  @FXML
  AnchorPane anchorPaneProducts;
  
  @FXML
  AnchorPane anchorPaneSales;
  
  @FXML
  HBox saleButton;
  
  @FXML
  HBox customerButton;
  
  @FXML
  HBox productButton;
  
  @FXML
  HBox logoutButton;
  
  AnchorPane currentAnchorPane;
  
  CustomerController customerController;
  
  @FXML
  public void refresh() {
    Task<List<Customer>> task = new Task<List<Customer>>() {
      @Override
      protected List<Customer> call() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Daniel", "123.123.123-32", "daniel@gmail.com", "88996134386"));
        customers.add(new Customer(2, "Gustavo", "364.634.234-63", "rafael@gmail.com", "88997451355"));
        return customers;
      }

      @Override
      protected void succeeded() {
        tableViewCustomers.getItems().clear();
        tableViewCustomers.getItems().addAll(getValue());
      }
    };

    new Thread(task).start();
  }

  /**
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.customerController = new CustomerController(RepositoryMethod.HIBERNATE);
    this.currentAnchorPane = this.anchorPaneCustomers;
    this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); 
    this.tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("name")); 
    this.tableColumnDocument.setCellValueFactory(new PropertyValueFactory<>("document")); 
    this.tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email")); 

    Task<List<Customer>> task = new Task<List<Customer>>() {
      @Override
      protected List<Customer> call() throws Exception {
        List<Customer> customers = customerController.list();
        return customers;
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
    currentAnchorPane.setVisible(false);
    currentAnchorPane = anchorPaneSales;
    currentAnchorPane.setVisible(true);
  }
  
  public void switchToCustomers() {
    currentAnchorPane.setVisible(false);
    currentAnchorPane = anchorPaneCustomers;
    currentAnchorPane.setVisible(true);
  }
  
  public void switchToProducts() {
    currentAnchorPane.setVisible(false);
    currentAnchorPane = anchorPaneProducts;
    currentAnchorPane.setVisible(true);
  }
  
  public void logout() {
    try {
      App.setRoot("view/login");
    } catch (IOException ex) {
      Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
