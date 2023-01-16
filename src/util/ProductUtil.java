package util;

import entities.Product;
import java.util.Optional;

public final class ProductUtil {
  public static Product fetchProduct(Product target, Product source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setName(Optional.ofNullable(source).map(Product::getName).orElse(target.getName()));
    target.setPrice(Optional.ofNullable(source).map(Product::getPrice).orElse(target.getPrice()));
    target.setMeasureUnit(
        Optional.ofNullable(source).map(Product::getMeasureUnit).orElse(target.getMeasureUnit()));
    target.setFinalProductProducts(
        Optional.ofNullable(source)
            .map(Product::getFinalProductProducts)
            .orElse(target.getFinalProductProducts()));
    target.setTaxes(Optional.ofNullable(source).map(Product::getTaxes).orElse(target.getTaxes()));
    return target;
  }
}
