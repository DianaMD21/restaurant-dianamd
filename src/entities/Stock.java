package entities;

import java.util.List;

public class Stock extends BaseEntity<Long>{
    private String name;
    private List<StockProduct> stockProducts;

    public Stock() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockProduct> getStockProducts() {
        return stockProducts;
    }

    public void setStockProducts(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }
}
