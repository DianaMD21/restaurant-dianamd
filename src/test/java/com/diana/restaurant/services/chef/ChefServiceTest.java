package com.diana.restaurant.services.chef;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.ChefServiceImpl;
import com.diana.restaurant.util.fixtures.ChefServiceFixtures;
import com.diana.restaurant.util.fixtures.UserFixtures;
import org.junit.Before;
import org.junit.Test;

public class ChefServiceTest {
  private ChefServiceImpl chefService;

  @Before
  public void setup() {
    chefService = Ioc.getInstance().get(IocServices.CHEF_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    chefService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    chefService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfChef() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.insert(chef);
    assertEquals(newChef, chefService.findById(chef.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.insert(chef);
    chefService.delete(newChef.getId());
    chefService.findById(newChef.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var chef = new Chef();
    chefService.update(chef);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    chefService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var chefExample = new Chef();
    chefExample.setId(UserFixtures.FAKEID);
    var chef = ChefServiceFixtures.buildChef(chefExample);
    chefService.update(chef);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.insert(chef);
    var updatedChefExample = new Chef();
    updatedChefExample.setName("Diana");
    updatedChefExample.setUsername("dxmd");
    var updatedChef = ChefServiceFixtures.buildChef(updatedChefExample);
    updatedChef.setId(chef.getId());
    assertEquals(newChef, chefService.update(updatedChef));
  }

  @Test
  public void insert_ShouldReturnChef() {
    var chef = ChefServiceFixtures.buildChef();
    assertEquals(chef, chefService.insert(chef));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var chef = new Chef();
    chefService.delete(chef.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var chef = ChefServiceFixtures.buildChef();
    var newChef = chefService.insert(chef);
    assertEquals(newChef, chefService.delete(chef.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfChefs() {
    var chefs = ChefServiceFixtures.buildChefs(3);
    chefs.stream().forEach(chefService::insert);
    assertEquals(chefs, chefService.findAll());
  }
}
