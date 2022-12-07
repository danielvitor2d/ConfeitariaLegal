package com.br.confeitarialegal.views;

import com.br.confeitarialegal.entities.Customer;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {
  private Customer customer;

  @FXML
  private TextField documentField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField idField;

  @FXML
  private TextField nameField;

  @FXML
  private TextField phoneNumberField;

  @FXML
  DialogPane dialogPane;

  public void setCustomer(Customer customer) {
    this.customer = customer;

    Bindings.bindBidirectional(idField.textProperty(), customer.idProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(documentField.textProperty(), customer.documentProperty());
    Bindings.bindBidirectional(emailField.textProperty(), customer.emailProperty());
    Bindings.bindBidirectional(nameField.textProperty(), customer.nameProperty());
    Bindings.bindBidirectional(phoneNumberField.textProperty(), customer.phoneNumberProperty());

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

  }

  private boolean validateFormData() {
    if (this.customer.getDocument().length() != 14) return false;
    if (this.customer.getName().length() < 2) return false;
    if (this.customer.getEmail().length() < 5) return false;
    return this.customer.getPhoneNumber().length() >= 8;
  }
}
