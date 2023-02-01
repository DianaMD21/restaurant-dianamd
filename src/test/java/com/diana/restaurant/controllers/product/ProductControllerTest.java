package com.diana.restaurant.controllers.product;

import com.diana.restaurant.controllers.ProductController;
import com.diana.restaurant.services.interfaces.ProductService;
import com.diana.restaurant.util.fixtures.services.ProductServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductControllerTest {
  private ProductController productController;
  private ProductService productServiceMock;

  @BeforeEach
  public void setup() {
    productServiceMock = Mockito.mock(ProductService.class);
    productController = new ProductController(productServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfProducts() {
    var products = ProductServiceFixtures.buildProducts(3);
    Mockito.when(productServiceMock.getAll()).thenReturn(products);
    var productsReturned = productController.getAll();
    Assertions.assertEquals(products, productsReturned);
    Mockito.verify(productServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddProduct() {
    var product = ProductServiceFixtures.buildProduct();
    var expectedProduct = ProductServiceFixtures.buildProduct(1L);
    Mockito.when(productServiceMock.add(Mockito.eq(product))).thenReturn(expectedProduct);
    var productAdded = productController.add(product);
    Assertions.assertEquals(expectedProduct, productAdded);
    Mockito.verify(productServiceMock, Mockito.times(1)).add(Mockito.eq(product));
  }

  @Test()
  public void delete_ShouldDeleteProduct() {
    var product = ProductServiceFixtures.buildProduct(1L);
    Mockito.when(productServiceMock.delete(product.getId())).thenReturn(product);
    var productDeleted = productController.deleteById(product.getId());
    Assertions.assertEquals(product, productDeleted);
    var productToTestVerify = ProductServiceFixtures.buildProduct(product);
    Mockito.verify(productServiceMock, Mockito.times(1))
        .delete(Mockito.eq(productToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundProduct() {
    var product = ProductServiceFixtures.buildProduct(1L);
    Mockito.when(productServiceMock.findById(product.getId())).thenReturn(Optional.of(product));
    var productFound = productController.findById(product.getId());
    Assertions.assertEquals(product, productFound);
    var productToTestVerify = ProductServiceFixtures.buildProduct(product);
    Mockito.verify(productServiceMock, Mockito.times(1))
        .findById(Mockito.eq(productToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateProduct() {
    var productToUpdate = ProductServiceFixtures.buildProduct(1L);
    productToUpdate.setName("Testing Product fake");
    Mockito.when(productServiceMock.update(productToUpdate)).thenReturn(productToUpdate);
    var productToTestVerify = ProductServiceFixtures.buildProduct(productToUpdate);
    var productUpdated = productController.update(productToUpdate);
    Assertions.assertEquals(productToUpdate, productUpdated);
    Mockito.verify(productServiceMock, Mockito.times(1)).update(Mockito.eq(productToTestVerify));
  }
}
