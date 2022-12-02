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
  requires java.base;
//  requires org.eclipse.persistence.jpa;
//  requires org.eclipse.persistence.core;
//  requires org.eclipse.persistence.jpa.modelgen.processor;
  requires de.jensd.fx.glyphs.fontawesome;

  opens com.br.confeitarialegal to javafx.fxml;
  opens com.br.confeitarialegal.view to javafx.fxml;
  opens com.br.confeitarialegal.entity;

  exports com.br.confeitarialegal;
  exports com.br.confeitarialegal.view;
}