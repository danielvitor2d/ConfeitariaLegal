package com.br.confeitarialegal.views;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.views.enums.Screens;
import com.br.confeitarialegal.views.utils.ManageScreens;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author danielvitor
 */
public class SalesViewController implements Initializable {

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
  
//  @FXML
//  public void refresh() {
//    Task<List<Customer>> task = new Task<List<Customer>>() {
//      @Override
//      protected List<Customer> call() throws Exception {
//        List<Customer> customers = new ArrayList<>();
//        customers.add(new Customer(1, "Daniel", "123.123.123-32", "daniel@gmail.com", "88996134386"));
//        customers.add(new Customer(2, "Gustavo", "364.634.234-63", "rafael@gmail.com", "88997451355"));
//        return customers;
//      }
//
//      @Override
//      protected void succeeded() {
//        tableViewCustomers.getItems().clear();
//        tableViewCustomers.getItems().addAll(getValue());
//      }
//    };
//
//    new Thread(task).start();
//  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.customerController = new CustomerController(RepositoryMethod.HIBERNATE);

//    this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
//    this.tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
//    this.tableColumnDocument.setCellValueFactory(new PropertyValueFactory<>("document"));
//    this.tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//    Task<List<Customer>> task = new Task<>() {
//      @Override
//      protected List<Customer> call() {
//        List<Customer> customers = customerController.list();
//        return customers;
//      }
//
//      @Override
//      protected void succeeded() {
//        tableViewCustomers.getItems().clear();
//        tableViewCustomers.getItems().addAll(this.getValue());
//      }
//    };

//    new Thread(task).start();
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
    try {
      App.setRoot(Screens.LOGIN.getRoute());
    } catch (IOException ex) {
      Logger.getLogger(SalesViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
