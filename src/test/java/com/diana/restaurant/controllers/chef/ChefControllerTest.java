package com.diana.restaurant.controllers.chef;

import com.diana.restaurant.controllers.ChefController;
import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.util.fixtures.services.ChefServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChefControllerTest {
  private ChefController chefController;

  @BeforeEach
  public void setup() {
    chefController = Ioc.getInstance().get(IocControllers.CHEF_CONTROLLER);
  }

  @Test
  public void getAll_ShouldReturnListOfChefs() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefController::add);
    Assertions.assertEquals(chefs, chefController.getAll());
  }

  @Test
  public void add_ShouldInsertChef() {
    var chef = ChefServiceFixtures.buildChef();
    Assertions.assertEquals(chef, chefController.add(chef));
  }

  @Test()
  public void delete_ShouldDeleteChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefController.add(chef);
    chefController.deleteById(chef.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> chefController.findById(newChef.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefController.add(chef);
    Assertions.assertEquals(newChef, chefController.findById(chef.getId()));
  }

  @Test
  public void update_ShouldUpdateChef() {
    var chef = ChefServiceFixtures.buildChef();
    var updatedChefExample = new Chef();
    updatedChefExample.setName("Diana");
    updatedChefExample.setUsername("dxmd");
    var newChef = chefController.add(chef);
    var updatedChef = ChefServiceFixtures.buildChef(updatedChefExample);
    updatedChef.setId(chef.getId());
    Assertions.assertEquals(newChef, chefController.update(updatedChef));
  }
}
