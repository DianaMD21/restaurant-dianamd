package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.FinalProduct;
import com.diana.restaurant.util.fixtures.entities.FinalProductFixtures;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class FinalProductServiceFixtures {

  public static List<FinalProduct> buildFinalProducts(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(FinalProductServiceFixtures::buildFinalProduct)
        .collect(Collectors.toList());
  }

  public static FinalProduct buildFinalProduct() {
    return buildFinalProduct((FinalProduct) null);
  }

  public static FinalProduct buildFinalProduct(Long id) {
    var finalProductExample = new FinalProduct();
    finalProductExample.setId(id);
    return buildFinalProduct(finalProductExample);
  }

  public static FinalProduct buildFinalProduct(FinalProduct finalProductExample) {
    var finalProduct = new FinalProduct();
    FinalProductFixtures.buildFinalProduct(finalProduct, finalProductExample);
    return finalProduct;
  }
}