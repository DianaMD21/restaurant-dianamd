package com.diana.restaurant.controllers.chef;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.controllers.ChefController;
import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.util.fixtures.ChefServiceFixtures;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChefControllerTest {
  private ChefController chefController;

  @Before
  public void setup() {
    chefController = Ioc.getInstance().get(IocControllers.CHEF_CONTROLLER);
  }

  @Test
  public void getAll_ShouldReturnListOfChefs() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefController::add);
    assertEquals(chefs, chefController.getAll());
  }

  @Test
  public void add_ShouldInsertAChef() {
    var chef = ChefServiceFixtures.buildChef();
    Assert.assertEquals(chef, chefController.add(chef));
  }

  @Test(expected = EntityNotFoundException.class)
  public void delete_ShouldDeleteAChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefController.add(chef);
    chefController.deleteById(chef.getId());
    chefController.findById(newChef.getId());
  }

  @Test
  public void findById_ShouldReturnFoundChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefController.add(chef);
    assertEquals(newChef, chefController.findById(chef.getId()));
  }

  @Test
  public void update_ShouldUpdateChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefController.add(chef);
    var updatedChefExample = new Chef();
    updatedChefExample.setName("Diana");
    updatedChefExample.setUsername("dxmd");
    var updatedChef = ChefServiceFixtures.buildChef(updatedChefExample);
    updatedChef.setId(chef.getId());
    assertEquals(newChef, chefController.update(updatedChef));
  }
}
