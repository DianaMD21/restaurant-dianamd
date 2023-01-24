package com.diana.restaurant.services.menu;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Menu;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.MenuServiceImpl;
import com.diana.restaurant.util.fixtures.MenuFixtures;
import com.diana.restaurant.util.fixtures.MenuServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class MenuServiceTest {
  private MenuServiceImpl menuService;

  @Before
  public void setup() {
    menuService = Ioc.getInstance().get(IocServices.MENU_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    menuService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    menuService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.insert(menu);
    assertEquals(newMenu, menuService.findById(menu.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.insert(menu);
    menuService.delete(newMenu.getId());
    menuService.findById(newMenu.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var menu = new Menu();
    menuService.update(menu);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    menuService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var menuExample = new Menu();
    menuExample.setId(MenuFixtures.FAKEID);
    var menu = MenuServiceFixtures.buildMenu(menuExample);
    menuService.update(menu);
  }

  @Test
  public void update_ShouldReturnUpdatedMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.insert(menu);
    var updatedMenuExample = new Menu();
    updatedMenuExample.setName("Diana");
    var updatedMenu = MenuServiceFixtures.buildMenu(updatedMenuExample);
    updatedMenu.setId(menu.getId());
    assertEquals(newMenu, menuService.update(updatedMenu));
  }

  @Test
  public void insert_ShouldInsertMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    assertEquals(menu, menuService.insert(menu));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var menu = new Menu();
    menuService.delete(menu.getId());
  }

  @Test
  public void delete_ShouldDeleteMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.insert(menu);
    assertEquals(newMenu, menuService.delete(menu.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfMenus() {
    var menus = MenuServiceFixtures.buildMenus(3);
    menus.stream().forEach(menuService::insert);
    assertEquals(menus, menuService.findAll());
  }
}
