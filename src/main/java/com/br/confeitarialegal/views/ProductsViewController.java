package com.br.confeitarialegal.views;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.controllers.ProductController;
import com.br.confeitarialegal.entities.Product;
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
import javafx.scene.control.cell.PropertyValueFactory;
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

  @FXML
  Button addProduct;

  @FXML
  Button updateProduct;

  @FXML
  Button deleteProduct;

  @FXML
  Button save;

  @FXML
  Button discard;

  private final ProductController productController = new ProductController(RepositoryMethod.HIBERNATE);

  private final ObservableList<Product> data = FXCollections.observableArrayList();

  private final ObservableList<Product> initialProducts = FXCollections.observableArrayList();

  private final List<Product> removedProducts = new ArrayList<>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    initializeTableView();

    updateProduct.disableProperty().bind(
            Bindings.isNull(tableViewProducts.getSelectionModel().selectedItemProperty())
    );

    deleteProduct.disableProperty().bind(
            Bindings.isNull(tableViewProducts.getSelectionModel().selectedItemProperty())
    );

    BooleanBinding bb = Bindings.createBooleanBinding(() -> Arrays.deepEquals(data.toArray(), initialProducts.toArray()), data, initialProducts);

    save.disableProperty().bind(
            bb
    );

    discard.disableProperty().bind(
            bb
    );
  }

  private void initializeTableView() {
    this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.tableColumnProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.tableColumnUnitaryType.setCellValueFactory(new PropertyValueFactory<>("unitaryType"));
    this.tableColumnUnitaryValue.setCellValueFactory(cellData -> new SimpleStringProperty(FormatData.toCurrencyBRL(cellData.getValue().getUnitaryValue())));

    populateTableView();
  }

  private void populateTableView() {
    Task<List<Product>> task = new Task<>() {
      @Override
      protected List<Product> call() {
        return productController.list();
      }

      @Override
      protected void succeeded() {
        initialProducts.clear();
        data.clear();
        this.getValue().forEach(product -> {
          initialProducts.add(product.createClone());
        });
        data.clear();
        data.setAll(List.copyOf(this.getValue()));
        tableViewProducts.setItems(data);
        tableViewProducts.refresh();
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
    Product product, backupProduct = null;
    int selectedIndex = -1;
    String dialogTitle = "";
    DialogMode dialogMode;

    if (event.getSource().equals(updateProduct)) {
      dialogMode = DialogMode.UPDATE;
      dialogTitle = "Atualizar produto";
      selectedIndex = tableViewProducts.getSelectionModel().getSelectedIndex();
      product = data.get(selectedIndex);
      backupProduct = product.createClone();
    } else if (event.getSource().equals(addProduct)) {
      dialogMode = DialogMode.ADD;
      dialogTitle = "Adicionar produto";
      product = new Product();
    } else {
      return;
    }

    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(App.class.getResource(Screens.MANAGE_PRODUCT.getRoute() + ".fxml"));
      DialogPane customerDialogPane = fxmlLoader.load();

      ManageProductController manageProductController = fxmlLoader.getController();
      manageProductController.setProduct(product);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setDialogPane(customerDialogPane);
      dialog.setTitle(dialogTitle);

      Optional<ButtonType> clickedButton = dialog.showAndWait();

      if (clickedButton.isPresent()) {
        if (clickedButton.get() == ButtonType.OK && dialogMode == DialogMode.ADD) {
          this.data.add(product);
        } else if (clickedButton.get() == ButtonType.CANCEL && dialogMode == DialogMode.UPDATE) {
          product.clone(backupProduct);
        } else if (clickedButton.get() == ButtonType.OK) {
          this.data.set(selectedIndex, product);
        }
      }

      tableViewProducts.getSelectionModel().clearSelection();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void handleDelete() {
    Optional<ButtonType> isConfirmed =  Alerts.showConfirmationDialog("Confirmar deleção", "Confirmação de deleção", "Tem certeza de que deseja deletar esse produto?");
    if (isConfirmed.isPresent() && isConfirmed.get() == ButtonType.OK) {
      Product productToBeRemoved = tableViewProducts.getSelectionModel().getSelectedItem();
      if (productToBeRemoved.getId() != 0) removedProducts.add(productToBeRemoved);
      int selectedIdx = tableViewProducts.getSelectionModel().getSelectedIndex();
      data.remove(selectedIdx);
    }
  }

  @FXML
  public void handleSave() {
    Task<Boolean> task = new Task<>() {
      @Override
      protected Boolean  call() {
        List<Product> changedProducts = new ArrayList<>();

        data.forEach(products -> {
          Optional<Product> optionalProduct = initialProducts.stream().filter(productsToCompare ->
                  productsToCompare.getId() == products.getId().intValue()
          ).findFirst();

          if (optionalProduct.isPresent()) {
            Product otherProduct = optionalProduct.get();
            if (!otherProduct.equals(products)) {
              changedProducts.add(products);
            }
          } else {
            changedProducts.add(products);
          }
        });

        return productController.saveChanges(changedProducts, removedProducts);
      }

      @Override
      protected void succeeded() {
        removedProducts.clear();
        populateTableView();
      }
    };

    new Thread(task).start();
  }

  @FXML
  public void handleDiscard() {
    removedProducts.clear();
    data.clear();
    initialProducts.forEach(customer -> {
      data.add(customer.createClone());
    });
  }

  @FXML
  public void handleKeyPress(KeyEvent event) {
    if (event.getCode() == KeyCode.ESCAPE) {
      this.tableViewProducts.getSelectionModel().clearSelection();
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
