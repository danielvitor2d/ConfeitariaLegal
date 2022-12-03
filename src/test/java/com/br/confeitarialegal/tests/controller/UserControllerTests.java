package com.br.confeitarialegal.tests.controller;

import org.junit.Test;
import org.junit.Assert;

import com.br.confeitarialegal.entity.User;
import com.br.confeitarialegal.controller.UserController;
import com.br.confeitarialegal.repository.RepositoryMethod;

/**
 *
 * @author Daniel Vitor
 */
public class UserControllerTests {
  
  @Test
  public void should_be_able_to_register() {
    UserController userController = new UserController(RepositoryMethod.IN_MEMORY);

    User user = userController.create("admin@admin.com", "pass1234admin");
    
    Assert.assertNotNull(user.getId());
  }
}
