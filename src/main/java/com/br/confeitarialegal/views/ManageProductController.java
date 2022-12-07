package com.br.confeitarialegal.views;

import com.br.confeitarialegal.entities.Product;
import com.br.confeitarialegal.entities.enums.UnitaryTypes;
import com.br.confeitarialegal.utils.CurrencyField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageProductController implements Initializable {

  private Product product;

  @FXML
  DialogPane dialogPane;

  @FXML
  private CurrencyField unitaryValueField;

  @FXML
  private ComboBox<UnitaryTypes> unitaryTypeField;

  @FXML
  private TextField idField;

  @FXML
  private TextField nameField;

  public void setProduct(Product product) {
    this.product = product;

    Bindings.bindBidirectional(idField.textProperty(), product.idProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(nameField.textProperty(), product.nameProperty());
    Bindings.bindBidirectional(unitaryValueField.amountProperty(), product.unitaryValueProperty());
    Bindings.bindBidirectional(unitaryTypeField.valueProperty(), product.unitaryTypeProperty());

    Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
    okButton.addEventFilter(
            ActionEvent.ACTION, event -> {
              if (!validateFormData()) {
                event.consume();
              }
            }
    );
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    unitaryTypeField.setItems(FXCollections.observableArrayList(UnitaryTypes.values()));
  }

  private boolean validateFormData() {
    return true;
  }
}
