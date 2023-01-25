package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.util.fixtures.entities.ProductFixtures;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class ProductServiceFixtures {

  public static List<Product> buildProducts(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(ProductServiceFixtures::buildProduct)
        .collect(Collectors.toList());
  }

  public static Product buildProduct() {
    return buildProduct((Product) null);
  }

  public static Product buildProduct(Long id) {
    var productExample = new Product();
    productExample.setId(id);
    return buildProduct(productExample);
  }

  public static Product buildProduct(Product productExample) {
    var product = new Product();
    ProductFixtures.buildProduct(product, productExample);
    return product;
  }
}