package com.diana.restaurant.services.menu;

import com.diana.restaurant.entities.Menu;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.MenuServiceImpl;
import com.diana.restaurant.util.fixtures.entities.MenuFixtures;
import com.diana.restaurant.util.fixtures.services.MenuServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuServiceTest {
  private MenuServiceImpl menuService;

  @BeforeEach
  public void setup() {
    menuService = Ioc.getInstance().get(IocServices.MENU_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> menuService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> menuService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.add(menu);
    Assertions.assertEquals(newMenu, menuService.findById(menu.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.add(menu);
    menuService.delete(newMenu.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> menuService.findById(newMenu.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var menu = new Menu();
    Assertions.assertThrows(IdNullPointerException.class, () -> menuService.update(menu));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> menuService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var menuExample = new Menu();
    menuExample.setId(MenuFixtures.FAKEID);
    var menu = MenuServiceFixtures.buildMenu(menuExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> menuService.update(menu));
  }

  @Test
  public void update_ShouldReturnUpdatedMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.add(menu);
    var updatedMenuExample = new Menu();
    updatedMenuExample.setName("Diana");
    var updatedMenu = MenuServiceFixtures.buildMenu(updatedMenuExample);
    updatedMenu.setId(menu.getId());
    Assertions.assertEquals(newMenu, menuService.update(updatedMenu));
  }

  @Test
  public void add_ShouldAddMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    Assertions.assertEquals(menu, menuService.add(menu));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var menu = new Menu();
    Assertions.assertThrows(IdNullPointerException.class, () -> menuService.delete(menu.getId()));
  }

  @Test
  public void delete_ShouldDeleteMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var newMenu = menuService.add(menu);
    Assertions.assertEquals(newMenu, menuService.delete(menu.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfMenus() {
    var menus = MenuServiceFixtures.buildMenus(3);
    menus.stream().forEach(menuService::add);
    Assertions.assertEquals(menus, menuService.getAll());
  }
}
