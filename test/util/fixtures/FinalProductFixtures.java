package util.fixtures;

import entities.FinalProduct;
import java.util.Optional;

public final class FinalProductFixtures {
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