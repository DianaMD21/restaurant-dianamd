package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Menu;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.MenuService;
import java.util.List;

public class MenuController {
  private MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  public List<Menu> getAll() {
    return this.menuService.getAll();
  }

  public Menu findById(Long id) {
    return this.menuService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Menu.class, id));
  }

  public Menu add(Menu menu) {
    return this.menuService.add(menu);
  }

  public Menu deleteById(Long id) {
    return this.menuService.delete(id);
  }

  public Menu update(Menu menu) {
    return this.menuService.update(menu);
  }
}
