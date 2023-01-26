package com.diana.restaurant.services.product;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.ProductServiceImpl;
import com.diana.restaurant.util.fixtures.entities.ProductFixtures;
import com.diana.restaurant.util.fixtures.services.ProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {
  private ProductServiceImpl productService;

  @BeforeEach
  public void setup() {
    productService = Ioc.getInstance().get(IocServices.PRODUCT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> productService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> productService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.add(product);
    Assertions.assertEquals(newProduct, productService.findById(product.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.add(product);
    productService.delete(newProduct.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> productService.findById(newProduct.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var product = new Product();
    Assertions.assertThrows(IdNullPointerException.class, () -> productService.update(product));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> productService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var productExample = new Product();
    productExample.setId(ProductFixtures.FAKEID);
    var product = ProductServiceFixtures.buildProduct(productExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> productService.update(product));
  }

  @Test
  public void update_ShouldReturnUpdatedProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var updatedProductExample = new Product();
    updatedProductExample.setName("Diana");
    updatedProductExample.setPrice(500.6);
    var newProduct = productService.add(product);
    var updatedProduct = ProductServiceFixtures.buildProduct(updatedProductExample);
    updatedProduct.setId(product.getId());
    Assertions.assertEquals(newProduct, productService.update(updatedProduct));
  }

  @Test
  public void add_ShouldAddProduct() {
    var product = ProductServiceFixtures.buildProduct();
    Assertions.assertEquals(product, productService.add(product));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var product = new Product();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> productService.delete(product.getId()));
  }

  @Test
  public void delete_ShouldDeleteProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.add(product);
    Assertions.assertEquals(newProduct, productService.delete(product.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfProducts() {
    var products = ProductServiceFixtures.buildProducts(3);
    products.stream().forEach(productService::add);
    Assertions.assertEquals(products, productService.getAll());
  }
}
