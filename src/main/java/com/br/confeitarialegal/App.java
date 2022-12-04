package com.br.confeitarialegal;

import com.br.confeitarialegal.views.enums.Screens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML(this.getInitialScreen()), 1366, 768);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {    
    launch();
  }
  
  private String getInitialScreen() {
    return Screens.CUSTOMERS.getRoute();
    
//    String screen = Screens.REGISTRATION.getRoute();;
//
//    UserController userController = new UserController(RepositoryMethod.HIBERNATE);
//    int length = userController.getLength();
//
//    if (length > 0)
//      screen = Screens.LOGIN.getRoute();;
//
//    return screen;
  }
}
