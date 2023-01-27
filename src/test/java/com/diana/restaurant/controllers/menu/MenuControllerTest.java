package com.diana.restaurant.controllers.menu;

import com.diana.restaurant.controllers.MenuController;
import com.diana.restaurant.services.interfaces.MenuService;
import com.diana.restaurant.util.fixtures.services.MenuServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuControllerTest {
  private MenuController menuController;
  private MenuService menuServiceMock;

  @BeforeEach
  public void setup() {
    menuServiceMock = Mockito.mock(MenuService.class);
    menuController = new MenuController(menuServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfMenus() {
    var menus = MenuServiceFixtures.buildMenus(3);
    Mockito.when(menuServiceMock.getAll()).thenReturn(menus);
    var menusReturned = menuController.getAll();
    Assertions.assertEquals(menus, menusReturned);
    Mockito.verify(menuServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddMenu() {
    var menu = MenuServiceFixtures.buildMenu();
    var expectedMenu = MenuServiceFixtures.buildMenu(1L);
    Mockito.when(menuServiceMock.add(Mockito.eq(menu))).thenReturn(expectedMenu);
    var menuAdded = menuController.add(menu);
    Assertions.assertEquals(expectedMenu, menuAdded);
    Mockito.verify(menuServiceMock, Mockito.times(1)).add(Mockito.eq(menu));
  }

  @Test()
  public void delete_ShouldDeleteMenu() {
    var menu = MenuServiceFixtures.buildMenu(1L);
    Mockito.when(menuServiceMock.delete(menu.getId())).thenReturn(menu);
    var menuDeleted = menuController.deleteById(menu.getId());
    Assertions.assertEquals(menu, menuDeleted);
    var menuToTestVerify = MenuServiceFixtures.buildMenu(menu);
    Mockito.verify(menuServiceMock, Mockito.times(1)).delete(Mockito.eq(menuToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundMenu() {
    var menu = MenuServiceFixtures.buildMenu(1L);
    Mockito.when(menuServiceMock.findById(menu.getId())).thenReturn(Optional.of(menu));
    var menuFound = menuController.findById(menu.getId());
    Assertions.assertEquals(menu, menuFound);
    var menuToTestVerify = MenuServiceFixtures.buildMenu(menu);
    Mockito.verify(menuServiceMock, Mockito.times(1))
        .findById(Mockito.eq(menuToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateMenu() {
    var menuToUpdate = MenuServiceFixtures.buildMenu(1L);
    menuToUpdate.setName("Testing Menu fake");
    Mockito.when(menuServiceMock.update(menuToUpdate)).thenReturn(menuToUpdate);
    var menuToTestVerify = MenuServiceFixtures.buildMenu(menuToUpdate);
    var menuUpdated = menuController.update(menuToUpdate);
    Assertions.assertEquals(menuToUpdate, menuUpdated);
    Mockito.verify(menuServiceMock, Mockito.times(1)).update(Mockito.eq(menuToTestVerify));
  }
}
