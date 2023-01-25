package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.FinalProduct;
import java.util.Optional;

public final class FinalProductFixtures {
  public static final Long FAKEID = 10L;

  public static FinalProduct buildFinalProduct(
      FinalProduct finalProduct, FinalProduct finalProductExample) {
    ProductFixtures.buildProduct(finalProduct, finalProductExample);
    finalProduct.setFinalProductProducts(
        Optional.ofNullable(finalProductExample)
            .map(FinalProduct::getFinalProductProducts)
            .orElse(null));
    return finalProduct;
  }
}
