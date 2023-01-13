package util.fixtures;

import entities.Menu;

import java.util.Optional;

public final class MenuFixtures {
    public static final Long FAKEID = 10L;

    public static Menu buildMenu(Menu menu, Menu menuExample) {
        BaseEntityFixtures.buildBaseEntity(menu, menuExample);
        menu.setName(
                Optional.ofNullable(menuExample)
                        .map(Menu::getName)
                        .orElse("testing-menu-name"));
        menu.setMenuProducts(
                Optional.ofNullable(menuExample)
                        .map(Menu::getMenuProducts)
                        .orElse(ProductServiceFixtures.buildProducts(3)));
        return menu;
    }
}
