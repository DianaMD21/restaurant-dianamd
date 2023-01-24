package com.diana.restaurant.services.product;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Product;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.ProductServiceImpl;
import com.diana.restaurant.util.fixtures.ProductFixtures;
import com.diana.restaurant.util.fixtures.ProductServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceTest {
  private ProductServiceImpl productService;

  @Before
  public void setup() {
    productService = Ioc.getInstance().get(IocServices.PRODUCT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    productService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    productService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.insert(product);
    assertEquals(newProduct, productService.findById(product.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.insert(product);
    productService.delete(newProduct.getId());
    productService.findById(newProduct.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var product = new Product();
    productService.update(product);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    productService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var productExample = new Product();
    productExample.setId(ProductFixtures.FAKEID);
    var product = ProductServiceFixtures.buildProduct(productExample);
    productService.update(product);
  }

  @Test
  public void update_ShouldReturnUpdatedProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.insert(product);
    var updatedProductExample = new Product();
    updatedProductExample.setName("Diana");
    updatedProductExample.setPrice(500.6);
    var updatedProduct = ProductServiceFixtures.buildProduct(updatedProductExample);
    updatedProduct.setId(product.getId());
    assertEquals(newProduct, productService.update(updatedProduct));
  }

  @Test
  public void insert_ShouldInsertProduct() {
    var product = ProductServiceFixtures.buildProduct();
    assertEquals(product, productService.insert(product));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var product = new Product();
    productService.delete(product.getId());
  }

  @Test
  public void delete_ShouldDeleteProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var newProduct = productService.insert(product);
    assertEquals(newProduct, productService.delete(product.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfProducts() {
    var products = ProductServiceFixtures.buildProducts(3);
    products.stream().forEach(productService::insert);
    assertEquals(products, productService.findAll());
  }
}
