package util;

import entities.StockProduct;

import java.util.Optional;

public final class StockProductUtil {
    public static StockProduct fetchStockProduct(StockProduct target, StockProduct source) {
        BaseEntityUtil.fetchBaseEntity(target, source);
        target.setProduct(Optional.ofNullable(source).map(StockProduct::getProduct).orElse(target.getProduct()));
        target.setStock(Optional.ofNullable(source).map(StockProduct::getStock).orElse(target.getStock()));
        target.setQuantity(
                Optional.ofNullable(source).map(StockProduct::getQuantity).orElse(target.getQuantity()));
        return target;
    }
}
