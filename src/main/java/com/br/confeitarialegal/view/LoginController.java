package com.br.confeitarialegal.view;

import com.br.confeitarialegal.controller.UserController;
import com.br.confeitarialegal.repository.RepositoryMethod;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danielvitor
 */
public class LoginController {

  @FXML
  AnchorPane anchorPane;

  @FXML
  FontAwesomeIconView togglePasswordVisible;

  @FXML
  TextField textFieldEmail;

  @FXML
  PasswordField passwordField;

  @FXML
  Tooltip passwordTooltip;

  private boolean showPassword;

  Stage stage;

  public LoginController() {
    this.showPassword = false;
  }

  private void showPassword() {
    if (this.stage == null) {
      this.stage = (Stage) anchorPane.getScene().getWindow();
    }

    Point2D p = passwordField.localToScene(passwordField.getBoundsInLocal().getMaxX(), passwordField.getBoundsInLocal().getMinY());
    passwordTooltip.setText(passwordField.getText());
    passwordTooltip.show(passwordField,
            p.getX() + stage.getScene().getX() + stage.getX(),
            p.getY() + stage.getScene().getY() + stage.getY());
  }

  private void hidePassword() {
    passwordTooltip.setText("");
    passwordTooltip.hide();
  }

  private void handleLogin() {
    Alert alert;

    String passwordText = passwordField.getText();
    String email = textFieldEmail.getText();

    if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
      alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("E-mail inválido");
      alert.setContentText("Digite um e-mail válido");

      alert.show();
      return;
    }

    UserController userController = new UserController(RepositoryMethod.HIBERNATE);

    if (userController.login(email, passwordText)) {
      alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Logou");
      alert.setContentText("Logou");
      alert.show();
    } else {
      alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Não logou");
      alert.setContentText("Não logou");
      alert.show();
    }
  }
  
  public void onMouseClickTogglePassword() {
    passwordTooltip.setText(passwordField.getText());
    
    if (showPassword == true) {
      togglePasswordVisible.setIcon(FontAwesomeIcon.EYE_SLASH);
      hidePassword();
    } else {
      togglePasswordVisible.setIcon(FontAwesomeIcon.EYE);
      showPassword();
    }

    showPassword = !showPassword;
  }
  
  public void onPressEnter(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      this.handleLogin(); 
    }
  }
  
  public void onMouseClickLogin() {
    this.handleLogin();
  }
}
