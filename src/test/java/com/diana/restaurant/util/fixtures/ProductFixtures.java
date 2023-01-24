package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.enums.MeasureUnitEnum;
import java.util.Optional;

public final class ProductFixtures {
  public static final Long FAKEID = 10L;

  public static Product buildProduct(Product product, Product productExample) {
    BaseEntityFixtures.buildBaseEntity(product, productExample);
    product.setName(
        Optional.ofNullable(productExample).map(Product::getName).orElse("testing-name"));
    product.setPrice(Optional.ofNullable(productExample).map(Product::getPrice).orElse(100.5));
    product.setMeasureUnit(
        Optional.ofNullable(productExample)
            .map(Product::getMeasureUnit)
            .orElse(MeasureUnitEnum.POUNDS));
    product.setFinalProductProducts(
        Optional.ofNullable(productExample).map(Product::getFinalProductProducts).orElse(null));
    product.setTaxes(Optional.ofNullable(productExample).map(Product::getTaxes).orElse(null));
    return product;
  }
}
