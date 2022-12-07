package com.br.confeitarialegal.views;

import com.br.confeitarialegal.controllers.CustomerController;
import com.br.confeitarialegal.entities.Customer;
import com.br.confeitarialegal.entities.Sale;
import com.br.confeitarialegal.entities.enums.PaymentTypes;
import com.br.confeitarialegal.entities.enums.StatusType;
import com.br.confeitarialegal.repositories.RepositoryMethod;
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

public class ManageSaleController implements Initializable {

  private Sale sale;

  @FXML
  private DatePicker createdAtField;

  @FXML
  private ComboBox<Customer> customerField;

  @FXML
  private DialogPane dialogPane;

  @FXML
  private TextField idField;

  @FXML
  private DatePicker paymentDateField;

  @FXML
  private CurrencyField totalValueField;

  @FXML
  private ComboBox<PaymentTypes> paymentTypeField;

  @FXML
  private ComboBox<StatusType> statusField;

  public void setSale(Sale sale) {
    this.sale = sale;

    Bindings.bindBidirectional(idField.textProperty(), sale.idProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(customerField.valueProperty(), sale.customerProperty());
    Bindings.bindBidirectional(paymentTypeField.valueProperty(), sale.paymentTypeProperty());
    Bindings.bindBidirectional(createdAtField.valueProperty(), sale.paymentDateProperty());
    Bindings.bindBidirectional(paymentDateField.valueProperty(), sale.createdAtProperty());
    Bindings.bindBidirectional(statusField.valueProperty(), sale.statusProperty());
    Bindings.bindBidirectional(totalValueField.amountProperty(), sale.totalValueProperty());

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
    statusField.setItems(FXCollections.observableArrayList(StatusType.values()));
    paymentTypeField.setItems(FXCollections.observableArrayList(PaymentTypes.values()));

    CustomerController customerController = new CustomerController(RepositoryMethod.HIBERNATE);

    customerField.setItems(FXCollections.observableArrayList(customerController.list()));
  }

  private boolean validateFormData() {
    return true;
  }
}
