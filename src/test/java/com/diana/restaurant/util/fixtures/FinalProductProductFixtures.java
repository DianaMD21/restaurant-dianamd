package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.FinalProductProduct;
import java.util.Optional;

public final class FinalProductProductFixtures {
  public static final Long FAKEID = 10L;

  public static FinalProductProduct buildFinalProductProduct(
      FinalProductProduct finalProductProduct, FinalProductProduct finalProductProductExample) {
    BaseEntityFixtures.buildBaseEntity(finalProductProduct, finalProductProductExample);
    finalProductProduct.setFinalProduct(
        Optional.ofNullable(finalProductProductExample)
            .map(FinalProductProduct::getFinalProduct)
            .orElse(FinalProductServiceFixtures.buildFinalProduct()));
    finalProductProduct.setProduct(
        Optional.ofNullable(finalProductProductExample)
            .map(FinalProductProduct::getProduct)
            .orElse(ProductServiceFixtures.buildProduct()));
    finalProductProduct.setQuantity(
        Optional.ofNullable(finalProductProductExample)
            .map(FinalProductProduct::getQuantity)
            .orElse(100.46));
    return finalProductProduct;
  }
}
