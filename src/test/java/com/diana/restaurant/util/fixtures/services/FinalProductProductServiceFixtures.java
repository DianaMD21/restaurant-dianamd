package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.FinalProductProduct;
import com.diana.restaurant.util.fixtures.entities.FinalProductProductFixtures;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class FinalProductProductServiceFixtures {

  public static List<FinalProductProduct> buildFinalProductProducts(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(FinalProductProductServiceFixtures::buildFinalProductProduct)
        .collect(Collectors.toList());
  }

  public static FinalProductProduct buildFinalProductProduct() {
    return buildFinalProductProduct((FinalProductProduct) null);
  }

  public static FinalProductProduct buildFinalProductProduct(Long id) {
    var finalProductProductExample = new FinalProductProduct();
    finalProductProductExample.setId(id);
    return buildFinalProductProduct(finalProductProductExample);
  }

  public static FinalProductProduct buildFinalProductProduct(
      FinalProductProduct finalProductProductExample) {
    var finalProductProduct = new FinalProductProduct();
    FinalProductProductFixtures.buildFinalProductProduct(
        finalProductProduct, finalProductProductExample);
    return finalProductProduct;
  }
}
