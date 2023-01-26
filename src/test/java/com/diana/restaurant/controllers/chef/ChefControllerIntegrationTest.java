package com.diana.restaurant.controllers.chef;

import com.diana.restaurant.controllers.ChefController;
import com.diana.restaurant.services.implementation.ChefServiceImpl;
import com.diana.restaurant.services.interfaces.ChefService;
import com.diana.restaurant.util.fixtures.services.ChefServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChefControllerIntegrationTest {
  private ChefController chefController;
  private ChefService chefServiceSpy;

  @BeforeEach
  public void setUp() {
    ChefService chefService = new ChefServiceImpl();
    chefServiceSpy = Mockito.spy(chefService);
    chefController = new ChefController(chefServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundChef() {
    var chef = ChefServiceFixtures.buildChef();
    chefController.add(chef);
    var chefFound = chefController.findById(chef.getId());
    Assertions.assertEquals(chefFound, chef);
    Mockito.verify(chefServiceSpy, Mockito.times(1)).findById(Mockito.eq(chef.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllChefs() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefController::add);
    var chefsFound = chefController.getAll();
    Assertions.assertEquals(chefs, chefsFound);
  }

  @Test
  public void add_ShouldAddChef() {
    var chef = ChefServiceFixtures.buildChef();
    var chefAdded = chefController.add(chef);
    Assertions.assertEquals(chefAdded, chef);
    Mockito.verify(chefServiceSpy, Mockito.times(1)).add(Mockito.eq(chef));
  }

  @Test
  public void delete_ShouldDeleteChef() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefController::add);
    var deletedChef = chefController.deleteById(chefs.get(0).getId());
    var deletedChefToVerify = ChefServiceFixtures.buildChef(chefs.get(0));
    Assertions.assertEquals(deletedChef, chefs.get(0));
    Mockito.verify(chefServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedChefToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateChef() {
    var chefToUpdate = ChefServiceFixtures.buildChef();
    chefController.add(chefToUpdate);
    chefToUpdate.setName("DIANA MARIA");
    chefToUpdate.setLastName("Monegro Diaz");
    var chefToTestVerify = ChefServiceFixtures.buildChef(chefToUpdate);
    var updatedChef = chefController.update(chefToUpdate);
    Assertions.assertEquals(updatedChef, chefToUpdate);
    Mockito.verify(chefServiceSpy, Mockito.times(1)).update(Mockito.eq(chefToTestVerify));
  }
}
