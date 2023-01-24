package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.Menu;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class MenuServiceFixtures {
  public static List<Menu> buildMenus(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(MenuServiceFixtures::buildMenu)
        .collect(Collectors.toList());
  }

  public static Menu buildMenu() {
    return buildMenu((Menu) null);
  }

  public static Menu buildMenu(Long id) {
    var menuExample = new Menu();
    menuExample.setId(id);
    return buildMenu(menuExample);
  }

  public static Menu buildMenu(Menu menuExample) {
    var menu = new Menu();
    MenuFixtures.buildMenu(menu, menuExample);
    return menu;
  }
}
