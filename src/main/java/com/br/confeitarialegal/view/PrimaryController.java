package com.br.confeitarialegal.view;

import com.br.confeitarialegal.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

  @FXML
  private void switchToSecondary() throws IOException {
    App.setRoot("view/login");
  }

}
