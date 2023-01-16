package util;

import entities.Menu;
import entities.Product;

import java.util.Optional;

public final class MenuUtil {
    public static Menu fetchMenu(Menu target, Menu source) {
        BaseEntityUtil.fetchBaseEntity(target, source);
        target.setName(Optional.ofNullable(source).map(Menu::getName).orElse(target.getName()));
        target.setMenuProducts(Optional.ofNullable(source).map(Menu::getMenuProducts).orElse(target.getMenuProducts()));
        return target;
    }
}
