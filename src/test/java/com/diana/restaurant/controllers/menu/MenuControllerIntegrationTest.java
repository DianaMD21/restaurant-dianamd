package com.diana.restaurant.controllers.menu;

import com.diana.restaurant.controllers.MenuController;
import com.diana.restaurant.services.implementation.MenuServiceImpl;
import com.diana.restaurant.services.interfaces.MenuService;
import com.diana.restaurant.util.fixtures.services.MenuServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuControllerIntegrationTest {
  private MenuController menuController;
  private MenuService menuServiceSpy;

  @BeforeEach
  public void setUp() {
    MenuService menuService = new MenuServiceImpl();
    menuServiceSpy = Mockito.spy(menuService);
    menuController = new MenuController(menuServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    menuController.add(menu);
    var menuFound = menuController.findById(menu.getId());
    Assertions.assertEquals(menuFound, menu);
    Mockito.verify(menuServiceSpy, Mockito.times(1)).findById(Mockito.eq(menu.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllMenus() {
    var menus = MenuServiceFixtures.buildMenus(3);
    menus.stream().forEach(menuController::add);
    var menusFound = menuController.getAll();
    Assertions.assertEquals(menus, menusFound);
  }

  @Test
  public void add_ShouldAddMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var menuAdded = menuController.add(menu);
    Assertions.assertEquals(menuAdded, menu);
    Mockito.verify(menuServiceSpy, Mockito.times(1)).add(Mockito.eq(menu));
  }

  @Test
  public void delete_ShouldDeleteMenu() {
    var menus = MenuServiceFixtures.buildMenus(3);
    menus.stream().forEach(menuController::add);
    var deletedMenu = menuController.deleteById(menus.get(0).getId());
    var deletedMenuToVerify = MenuServiceFixtures.buildMenu(menus.get(0));
    Assertions.assertEquals(deletedMenu, menus.get(0));
    Mockito.verify(menuServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedMenuToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateMenu() {
    var menuToUpdate = MenuServiceFixtures.buildMenu();
    menuController.add(menuToUpdate);
    menuToUpdate.setName("Testing Menu fake");
    var menuToTestVerify = MenuServiceFixtures.buildMenu(menuToUpdate);
    var updatedMenu = menuController.update(menuToUpdate);
    Assertions.assertEquals(updatedMenu, menuToUpdate);
    Mockito.verify(menuServiceSpy, Mockito.times(1)).update(Mockito.eq(menuToTestVerify));
  }
}
