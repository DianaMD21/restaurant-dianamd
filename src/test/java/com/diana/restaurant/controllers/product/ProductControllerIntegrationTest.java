package com.diana.restaurant.controllers.product;

import com.diana.restaurant.controllers.ProductController;
import com.diana.restaurant.services.implementation.ProductServiceImpl;
import com.diana.restaurant.services.interfaces.ProductService;
import com.diana.restaurant.util.fixtures.services.ProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductControllerIntegrationTest {
  private ProductController productController;
  private ProductService productServiceSpy;

  @BeforeEach
  public void setUp() {
    ProductService productService = new ProductServiceImpl();
    productServiceSpy = Mockito.spy(productService);
    productController = new ProductController(productServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundProduct() {
    var product = ProductServiceFixtures.buildProduct();
    productController.add(product);
    var productFound = productController.findById(product.getId());
    Assertions.assertEquals(productFound, product);
    Mockito.verify(productServiceSpy, Mockito.times(1)).findById(Mockito.eq(product.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllProducts() {
    var products = ProductServiceFixtures.buildProducts(3);
    products.stream().forEach(productController::add);
    var productsFound = productController.getAll();
    Assertions.assertEquals(products, productsFound);
  }

  @Test
  public void add_ShouldAddProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var productAdded = productController.add(product);
    Assertions.assertEquals(productAdded, product);
    Mockito.verify(productServiceSpy, Mockito.times(1)).add(Mockito.eq(product));
  }

  @Test
  public void delete_ShouldDeleteProduct() {
    var products = ProductServiceFixtures.buildProducts(3);
    products.stream().forEach(productController::add);
    var deletedProduct = productController.deleteById(products.get(0).getId());
    var deletedProductToVerify = ProductServiceFixtures.buildProduct(products.get(0));
    Assertions.assertEquals(deletedProduct, products.get(0));
    Mockito.verify(productServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedProductToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateProduct() {
    var productToUpdate = ProductServiceFixtures.buildProduct();
    productController.add(productToUpdate);
    productToUpdate.setName("Testing Product fake");
    var productToTestVerify = ProductServiceFixtures.buildProduct(productToUpdate);
    var updatedProduct = productController.update(productToUpdate);
    Assertions.assertEquals(updatedProduct, productToUpdate);
    Mockito.verify(productServiceSpy, Mockito.times(1)).update(Mockito.eq(productToTestVerify));
  }
}
