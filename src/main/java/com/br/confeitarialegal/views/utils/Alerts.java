package com.br.confeitarialegal.views.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

  public static Optional<ButtonType> showConfirmationDialog(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    return alert.showAndWait();
  }

  public static void showInformationDialog(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.show();
  }


}
