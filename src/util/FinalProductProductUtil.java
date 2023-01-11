package util;

import entities.FinalProductProduct;
import java.util.Optional;

public final class FinalProductProductUtil {
  public static FinalProductProduct fetchFinalProductProduct(
      FinalProductProduct target, FinalProductProduct source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setFinalProduct(
        Optional.ofNullable(source)
            .map(FinalProductProduct::getFinalProduct)
            .orElse(target.getFinalProduct()));
    target.setProduct(
        Optional.ofNullable(source)
            .map(FinalProductProduct::getProduct)
            .orElse(target.getProduct()));
    target.setQuantity(
        Optional.ofNullable(source)
            .map(FinalProductProduct::getQuantity)
            .orElse(target.getQuantity()));
    return target;
  }
}
