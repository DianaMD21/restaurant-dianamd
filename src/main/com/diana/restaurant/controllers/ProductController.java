package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.ProductService;
import java.util.List;

public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  public List<Product> getAll() {
    return this.productService.getAll();
  }

  public Product findById(Long id) {
    return this.productService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
  }

  public Product add(Product product) {
    return this.productService.add(product);
  }

  public Product deleteById(Long id) {
    return this.productService.delete(id);
  }

  public Product update(Product product) {
    return this.productService.update(product);
  }
}
