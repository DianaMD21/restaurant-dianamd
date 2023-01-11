package util;

import entities.Stock;
import java.util.Optional;

public final class StockUtil {
  public static Stock fetchStock(Stock target, Stock source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setName(Optional.ofNullable(source).map(Stock::getName).orElse(target.getName()));
    target.setStockProducts(
        Optional.ofNullable(source).map(Stock::getStockProducts).orElse(target.getStockProducts()));
    return target;
  }
}
