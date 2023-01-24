package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Menu;
import com.diana.restaurant.services.interfaces.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {
  public MenuServiceImpl() {
    super(0L, Menu.class, Long.class);
  }
}
