package entities;

import java.util.List;

public class Menu extends BaseEntity<Long>{
    private String name;
    private List<Product> menuProducts;

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getMenuProducts() {
        return menuProducts;
    }

    public void setMenuProducts(List<Product> menuProducts) {
        this.menuProducts = menuProducts;
    }
}
