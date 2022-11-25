package com.br.confeitarialegal;

import com.br.confeitarialegal.bean.Client;
import java.io.File;

import java.io.IOException;
import java.util.UUID;

import javafx.fxml.FXML;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SecondaryController {

  @FXML
  private void switchToPrimary() throws IOException {
    SessionFactory sessionFactory = new Configuration().configure(new File("src/main/java/conf/hibernate.cfg.xml")).buildSessionFactory();
    
    Session session = sessionFactory.openSession();
    
    Client client = new Client();
    client.setId(UUID.randomUUID());
    client.setName("Daniel");

    session.beginTransaction();
    session.save(client);
    
    session.getTransaction().commit();
    session.close();
    
    App.setRoot("primary");
  }
}