package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.services.interfaces.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {
  public ProductServiceImpl() {
    super(0L, Product.class, Long.class);
  }
}
