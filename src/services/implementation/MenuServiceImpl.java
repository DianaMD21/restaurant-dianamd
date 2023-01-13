package services.implementation;

import entities.Menu;
import services.interfaces.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {
    public MenuServiceImpl() {
        super(0L, Menu.class, Long.class);
    }
}
