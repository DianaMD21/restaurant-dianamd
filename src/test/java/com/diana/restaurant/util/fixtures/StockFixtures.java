package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.Stock;
import java.util.Optional;

public final class StockFixtures {
  public static final Long FAKEID = 10L;

  public static Stock buildStock(Stock stock, Stock stockExample) {
    BaseEntityFixtures.buildBaseEntity(stock, stockExample);
    stock.setName(Optional.ofNullable(stockExample).map(Stock::getName).orElse("testing-name"));
    stock.setStockProducts(
        Optional.ofNullable(stockExample).map(Stock::getStockProducts).orElse(null));
    return stock;
  }
}
