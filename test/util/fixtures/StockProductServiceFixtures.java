package util.fixtures;

import entities.StockProduct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class StockProductServiceFixtures {

    public static StockProduct buildStockProduct() {
        return buildStockProduct((StockProduct) null);
    }

    public static StockProduct buildStockProduct(Long id) {
        var stockProductExample = new StockProduct();
        stockProductExample.setId(id);
        return buildStockProduct(stockProductExample);
    }

    public static StockProduct buildStockProduct(StockProduct stockProductExample) {
        var stockProduct = new StockProduct();
        StockProductFixtures.buildStockProduct(stockProduct, stockProductExample);
        return stockProduct;
    }
}
