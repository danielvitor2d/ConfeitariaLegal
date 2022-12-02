package com.br.confeitarialegal.tests.controller;

import org.junit.Test;

// import javax.persistence.EntityManager;
// import javax.persistence.EntityManagerFactory;
// import javax.persistence.Persistence;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.controller.UserController;
import com.br.confeitarialegal.repository.interfaces.IUserRepository;
import com.br.confeitarialegal.repository.implementations.in_memory.UserRepositoryInMemory;

/**
 *
 * @author Daniel Vitor
 */
public class UserControllerTests {
  
  @Test
  public void should_be_able_to_register() {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistencia_postgres_confeitaria_legal");
////
//    EntityManager entityManager = factory.createEntityManager();
////    

    IUserRepository userRepository = new UserRepositoryInMemory();

    UserController userController = new UserController(userRepository);

    User user = userController.create("admin@admin.com", "pass1234admin");
    
    assert(user.getId() != null);
  }

}
