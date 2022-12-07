package com.br.confeitarialegal.views;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.controllers.SaleController;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.repositories.RepositoryMethod;
import com.br.confeitarialegal.utils.FormatData;
import com.br.confeitarialegal.views.enums.DialogMode;
import com.br.confeitarialegal.views.enums.Screens;
import com.br.confeitarialegal.views.utils.Alerts;
import com.br.confeitarialegal.views.utils.ManageScreens;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;

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
  private TableView<Sale> tableViewSales;

  @FXML
  Button addSale;

  @FXML
  Button updateSale;

  @FXML
  Button deleteSale;

  @FXML
  Button save;

  @FXML
  Button discard;

  SaleController saleController = new SaleController(RepositoryMethod.HIBERNATE);

  private final ObservableList<Sale> data = FXCollections.observableArrayList();

  private final ObservableList<Sale> initialSales = FXCollections.observableArrayList();

  private final List<Sale> removedSales = new ArrayList<>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    initializeTableView();

    updateSale.disableProperty().bind(
            Bindings.isNull(tableViewSales.getSelectionModel().selectedItemProperty())
    );

    deleteSale.disableProperty().bind(
            Bindings.isNull(tableViewSales.getSelectionModel().selectedItemProperty())
    );

    BooleanBinding bb = Bindings.createBooleanBinding(() -> Arrays.deepEquals(data.toArray(), initialSales.toArray()), data, initialSales);

    save.disableProperty().bind(
            bb
    );

    discard.disableProperty().bind(
            bb
    );
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
    this.tableColumnCustomer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getName()));
    this.tableColumnDate.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toLocaleDate(cellData.getValue().getCreatedAt())));
    this.tableColumnPaymentDate.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.maskNull(FormatData.toLocaleDate(cellData.getValue().getPaymentDate()), "Não pago")));
    this.tableColumnPaymentType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaymentType().getPaymentType()));
    this.tableColumnStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().getStatus()));
    this.tableColumnTotal.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toCurrencyBRL(cellData.getValue().getTotalValue())));

    populateTableView();
  }

  private void populateTableView() {
    Task<List<Sale>> task = new Task<>() {
      @Override
      protected List<Sale> call() {
        return saleController.list();
      }

      @Override
      protected void succeeded() {
        initialSales.clear();
        this.getValue().forEach(sale -> {
          initialSales.add(sale.createClone());
        });
        data.clear();
        data.setAll(List.copyOf(this.getValue()));
        tableViewSales.setItems(data);
        tableViewSales.refresh();
      }
    };

    new Thread(task).start();
  }

  @FXML
  public void handleAdd(MouseEvent event) {
    System.out.println(Arrays.toString(initialSales.toArray()));
    System.out.println(Arrays.toString(data.toArray()));
    handleUpdate(event);
  }

  @FXML
  public void handleUpdate(MouseEvent event) {
    Sale sale, backupSale = null;
    int selectedIndex = -1;
    String dialogTitle = "";
    DialogMode dialogMode;

    if (event.getSource().equals(updateSale)) {
      dialogMode = DialogMode.UPDATE;
      dialogTitle = "Atualizar venda";
      selectedIndex = tableViewSales.getSelectionModel().getSelectedIndex();
      sale = data.get(selectedIndex);
      backupSale = sale.createClone();
    } else if (event.getSource().equals(addSale)) {
      dialogMode = DialogMode.ADD;
      dialogTitle = "Adicionar venda";
      sale = new Sale();
    } else {
      return;
    }

    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(App.class.getResource(Screens.MANAGE_SALE.getRoute() + ".fxml"));
      DialogPane customerDialogPane = fxmlLoader.load();

      ManageSaleController manageSaleController = fxmlLoader.getController();
      manageSaleController.setSale(sale);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setDialogPane(customerDialogPane);
      dialog.setTitle(dialogTitle);

      Optional<ButtonType> clickedButton = dialog.showAndWait();

      if (clickedButton.isPresent()) {
        if (clickedButton.get() == ButtonType.OK && dialogMode == DialogMode.ADD) {
          this.data.add(sale);
        } else if (clickedButton.get() == ButtonType.CANCEL && dialogMode == DialogMode.UPDATE) {
          sale.clone(backupSale);
        } else if (clickedButton.get() == ButtonType.OK) {
          this.data.set(selectedIndex, sale);
        }
      }

      tableViewSales.getSelectionModel().clearSelection();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void handleDelete() {
    Optional<ButtonType> isConfirmed =  Alerts.showConfirmationDialog("Confirmar deleção", "Confirmação de deleção", "Tem certeza de que deseja deletar essa venda?");
    if (isConfirmed.isPresent() && isConfirmed.get() == ButtonType.OK) {
      Sale saleToBeRemoved = tableViewSales.getSelectionModel().getSelectedItem();
      if (saleToBeRemoved.getId() != 0) removedSales.add(saleToBeRemoved);
      int selectedIdx = tableViewSales.getSelectionModel().getSelectedIndex();
      data.remove(selectedIdx);
    }
  }

  @FXML
  public void handleSave() {
    Task<Boolean> task = new Task<>() {
      @Override
      protected Boolean  call() {
        List<Sale> changedSales = new ArrayList<>();

        data.forEach(sales -> {
          Optional<Sale> optionalSale = initialSales.stream().filter(saleToCompare ->
                  saleToCompare.getId() == sales.getId().intValue()
          ).findFirst();

          if (optionalSale.isPresent()) {
            Sale otherSale = optionalSale.get();
            if (!otherSale.equals(sales)) {
              changedSales.add(sales);
            }
          } else {
            changedSales.add(sales);
          }
        });

        return saleController.saveChanges(changedSales, removedSales);
      }

      @Override
      protected void succeeded() {
        removedSales.clear();
        populateTableView();
      }
    };

    new Thread(task).start();
  }

  @FXML
  public void handleDiscard() {
    removedSales.clear();
    data.clear();
    initialSales.forEach(sale -> {
      data.add(sale.createClone());
    });
  }

  @FXML
  public void handleKeyPress(KeyEvent event) {
    if (event.getCode() == KeyCode.ESCAPE) {
      this.tableViewSales.getSelectionModel().clearSelection();
    }
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
