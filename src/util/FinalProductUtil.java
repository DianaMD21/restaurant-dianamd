package util;

import entities.FinalProduct;
import java.util.Optional;

public final class FinalProductUtil {
  public static FinalProduct fetchFinalProduct(FinalProduct target, FinalProduct source) {
    ProductUtil.fetchProduct(target, source);
    target.setFinalProductProducts(
        Optional.ofNullable(source)
            .map(FinalProduct::getFinalProductProducts)
            .orElse(target.getFinalProductProducts()));
    return target;
  }
}
