module com.br.confeitarialegal {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.persistence;
  requires org.postgresql.jdbc;
  requires org.hibernate.orm.core;
  requires java.naming;
  requires static lombok;
  requires javax.json;
  requires java.sql;

  opens com.br.confeitarialegal to javafx.fxml;
  opens com.br.confeitarialegal.bean;
  
  exports com.br.confeitarialegal;
}
