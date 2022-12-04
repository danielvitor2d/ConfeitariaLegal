package com.br.confeitarialegal.views;

import com.br.confeitarialegal.controllers.SaleController;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.utils.FormatData;
import com.br.confeitarialegal.views.utils.ManageScreens;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class SalesViewController implements Initializable {

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
  SaleController saleController;
  @FXML
  private TableColumn<Sale, String> tableColumnId;
  @FXML
  private TableColumn<Sale, String> tableColumnCustomer;
  @FXML
  private TableColumn<Sale, String> tableColumnDate;
  @FXML
  private TableColumn<Sale, String> tableColumnPaymentDate;
  @FXML
  private TableColumn<Sale, String> tableColumnPaymentType;
  @FXML
  private TableColumn<Sale, String> tableColumnStatus;
  @FXML
  private TableColumn<Sale, String> tableColumnTotal;
  @FXML
  private TableView<Sale> tableViewSale;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.saleController = new SaleController(RepositoryMethod.HIBERNATE);

    initializeTableView();
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
    this.tableColumnCustomer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getName()));
    this.tableColumnDate.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toLocaleDate(cellData.getValue().getCreatedAt())));
    this.tableColumnPaymentDate.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.maskNull(FormatData.toLocaleDate(cellData.getValue().getPaymentDate()), "Não pago")));
    this.tableColumnPaymentType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaymentType().getPaymentType()));
    this.tableColumnStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().getStatus()));
    this.tableColumnTotal.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toCurrencyBRL(cellData.getValue().getTotalValue())));

    Task<List<Sale>> task = new Task<>() {
      @Override
      protected List<Sale> call() {
        return saleController.list();
      }

      @Override
      protected void succeeded() {
        tableViewSale.getItems().clear();
        tableViewSale.getItems().addAll(this.getValue());
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
