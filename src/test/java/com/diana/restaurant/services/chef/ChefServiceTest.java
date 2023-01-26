package com.diana.restaurant.services.chef;

import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.ChefServiceImpl;
import com.diana.restaurant.util.fixtures.entities.UserFixtures;
import com.diana.restaurant.util.fixtures.services.ChefServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChefServiceTest {
  private ChefServiceImpl chefService;

  @BeforeEach
  public void setup() {
    chefService = Ioc.getInstance().get(IocServices.CHEF_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> chefService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> chefService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.add(chef);
    Assertions.assertEquals(newChef, chefService.findById(chef.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.add(chef);
    chefService.delete(newChef.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> chefService.findById(newChef.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var chef = new Chef();
    Assertions.assertThrows(IdNullPointerException.class, () -> chefService.update(chef));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> chefService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var chefExample = new Chef();
    chefExample.setId(UserFixtures.FAKEID);
    var chef = ChefServiceFixtures.buildChef(chefExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> chefService.update(chef));
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var chef = ChefServiceFixtures.buildChef();
    var updatedChefExample = new Chef();
    updatedChefExample.setName("Diana");
    updatedChefExample.setUsername("dxmd");
    var newChef = chefService.add(chef);
    var updatedChef = ChefServiceFixtures.buildChef(updatedChefExample);
    updatedChef.setId(chef.getId());
    Assertions.assertEquals(newChef, chefService.update(updatedChef));
  }

  @Test
  public void add_ShouldAddChef() {
    var chef = ChefServiceFixtures.buildChef();
    Assertions.assertEquals(chef, chefService.add(chef));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var chef = new Chef();
    Assertions.assertThrows(IdNullPointerException.class, () -> chefService.delete(chef.getId()));
  }

  @Test
  public void delete_ShouldDeleteEntity() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.add(chef);
    Assertions.assertEquals(newChef, chefService.delete(chef.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfChefs() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefService::add);
    Assertions.assertEquals(chefs, chefService.getAll());
  }
}
