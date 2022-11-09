package services.implementation;

import entities.Product;
import services.interfaces.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {
  public ProductServiceImpl() {
    super(0L, Product.class, Long.class);
  }
}
