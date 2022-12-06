package com.br.confeitarialegal.views;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.views.enums.Screens;
import com.br.confeitarialegal.views.utils.Alerts;
import com.br.confeitarialegal.views.utils.ManageScreens;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;

enum DialogMode { ADD, UPDATE };

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

  @FXML
  Button addCustomer;

  @FXML
  Button updateCustomer;

  @FXML
  Button deleteCustomer;

  @FXML
  Button save;

  @FXML
  Button discard;

  private final CustomerController customerController = new CustomerController(RepositoryMethod.HIBERNATE);

  private final ObservableList<Customer> data = FXCollections.observableArrayList();

  private final ObservableList<Customer> initialCustomers = FXCollections.observableArrayList();

  private final List<Customer> removedCustomers = new ArrayList<>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    initializeTableView();

    updateCustomer.disableProperty().bind(
            Bindings.isNull(tableViewCustomers.getSelectionModel().selectedItemProperty())
    );

    deleteCustomer.disableProperty().bind(
            Bindings.isNull(tableViewCustomers.getSelectionModel().selectedItemProperty())
    );

    BooleanBinding bb = Bindings.createBooleanBinding(() -> Arrays.deepEquals(data.toArray(), initialCustomers.toArray()), data, initialCustomers);

    save.disableProperty().bind(
            bb
    );

    discard.disableProperty().bind(
            bb
    );
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.tableColumnDocument.setCellValueFactory(new PropertyValueFactory<>("document"));
    this.tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

//    messgeLabel.textProperty().bind(
//            tableViewCustomers.getSelectionModel().selectedIndexProperty().asString()
//    );

    populateTableView();
  }

  private void populateTableView() {
    Task<List<Customer>> task = new Task<>() {
      @Override
      protected List<Customer> call() {
        return customerController.list();
      }

      @Override
      protected void succeeded() {
        initialCustomers.clear();
        data.clear();
        this.getValue().forEach(customer -> {
          initialCustomers.add(customer.createClone());
        });
        data.clear();
        data.setAll(List.copyOf(this.getValue()));
        tableViewCustomers.setItems(data);
        tableViewCustomers.refresh();
      }
    };

    new Thread(task).start();
  }

  @FXML
  public void handleAdd(MouseEvent event) {
    handleUpdate(event);
  }

  @FXML
  public void handleUpdate(MouseEvent event) {
    Customer customer, backupCustomer = null;
    int selectedIndex = -1;
    String dialogTitle = "";
    DialogMode dialogMode;

    if (event.getSource().equals(updateCustomer)) {
      dialogMode = DialogMode.UPDATE;
      dialogTitle = "Atualizar cliente";
      selectedIndex = tableViewCustomers.getSelectionModel().getSelectedIndex();
      customer = data.get(selectedIndex);
      backupCustomer = customer.createClone();
    } else if (event.getSource().equals(addCustomer)) {
      dialogMode = DialogMode.ADD;
      dialogTitle = "Adicionar cliente";
      customer = new Customer();
    } else {
      return;
    }

    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(App.class.getResource(Screens.UPDATE_CUSTOMER.getRoute() + ".fxml"));
      DialogPane customerDialogPane = fxmlLoader.load();

      UpdateCustomerController updateCustomerController = fxmlLoader.getController();
      updateCustomerController.setCustomer(customer);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setDialogPane(customerDialogPane);
      dialog.setTitle(dialogTitle);

      Optional<ButtonType> clickedButton = dialog.showAndWait();

      if (clickedButton.isPresent()) {
        if (clickedButton.get() == ButtonType.OK && dialogMode == DialogMode.ADD) {
          this.data.add(customer);
        } else if (clickedButton.get() == ButtonType.CANCEL && dialogMode == DialogMode.UPDATE) {
          customer.clone(backupCustomer);
        } else if (clickedButton.get() == ButtonType.OK) {
          this.data.set(selectedIndex, customer);
        }
      }

      tableViewCustomers.getSelectionModel().clearSelection();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void handleDelete() {
    Optional<ButtonType> isConfirmed =  Alerts.showConfirmationDialog("Confirmar deleção", "Confirmação de deleção", "Tem certeza de que deseja deletar esse cliente?");
    if (isConfirmed.isPresent() && isConfirmed.get() == ButtonType.OK) {
      Customer customerToBeRemoved = tableViewCustomers.getSelectionModel().getSelectedItem();
      if (customerToBeRemoved.getId() != 0) removedCustomers.add(customerToBeRemoved);
      int selectedIdx = tableViewCustomers.getSelectionModel().getSelectedIndex();
      data.remove(selectedIdx);
    }
  }

  @FXML
  public void handleSave() {
    Task<Boolean> task = new Task<>() {
      @Override
      protected Boolean  call() {
        List<Customer> changedCustomers = new ArrayList<>();

        data.forEach(customer -> {
          Optional<Customer> optionalCustomer = initialCustomers.stream().filter(customerToCompare ->
                          customerToCompare.getId() == customer.getId().intValue()
          ).findFirst();

          if (optionalCustomer.isPresent()) {
            Customer otherCustomer = optionalCustomer.get();
            if (!otherCustomer.equals(customer)) {
              changedCustomers.add(customer);
            }
          } else {
            changedCustomers.add(customer);
          }
        });

        return customerController.saveChanges(changedCustomers, removedCustomers);
      }

      @Override
      protected void succeeded() {
        removedCustomers.clear();
        populateTableView();
      }
    };

    new Thread(task).start();
  }

  @FXML
  public void handleDiscard() {
    removedCustomers.clear();
    data.clear();
    initialCustomers.forEach(customer -> {
      data.add(customer.createClone());
    });
  }

  public void handleKeyPress(KeyEvent event) {
      if (event.getCode() == KeyCode.ESCAPE) {
        this.tableViewCustomers.getSelectionModel().clearSelection();
      }
  }

  @FXML
  public void switchToSales() {
    ManageScreens.switchToSales();
  }

  @FXML
  public void switchToCustomers() {
    ManageScreens.switchToCustomers();
  }

  @FXML
  public void switchToProducts() {
    ManageScreens.switchToProducts();
  }

  @FXML
  public void logout() {
    ManageScreens.switchToLogin();
  }
}
