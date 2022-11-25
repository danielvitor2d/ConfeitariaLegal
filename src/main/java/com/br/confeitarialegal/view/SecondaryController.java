package com.br.confeitarialegal.view;



import com.br.confeitarialegal.App;
import com.br.confeitarialegal.model.entity.Client;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import javafx.fxml.FXML;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SecondaryController {

  @FXML
  private void switchToPrimary() throws IOException {
//    Path path = FileSystems.getDefault().getPath("src", "main", "java", "conf", "hibernate.cfg.xml");
//    SessionFactory sessionFactory = new Configuration().configure(path.toFile()).buildSessionFactory();
//    
//    Session session = sessionFactory.openSession();
//    
//    Client client = new Client();
//    client.setId(UUID.randomUUID());
//    client.setName("Daniel");
//
//    session.beginTransaction();
//    session.save(client);
//    
//    session.getTransaction().commit();
//    session.close();
    
    App.setRoot("view/primary");
  }
}