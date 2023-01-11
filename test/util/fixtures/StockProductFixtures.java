package util.fixtures;

import entities.StockProduct;

import java.util.Optional;

public final class StockProductFixtures {
  public static final Long FAKEID = 10L;

  public static StockProduct buildStockProduct(
      StockProduct stockProduct, StockProduct stockProductExample) {
    BaseEntityFixtures.buildBaseEntity(stockProduct, stockProductExample);
    stockProduct.setProduct(
        Optional.ofNullable(stockProductExample)
            .map(StockProduct::getProduct)
            .orElse(ProductServiceFixtures.buildProduct()));
    stockProduct.setStock(
        Optional.ofNullable(stockProductExample).map(StockProduct::getStock).orElse(null));
    stockProduct.setQuantity(
        Optional.ofNullable(stockProductExample).map(StockProduct::getQuantity).orElse(20.1));
    return stockProduct;
  }
}
