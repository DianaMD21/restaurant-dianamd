package com.diana.restaurant.controllers.stockProduct;

import com.diana.restaurant.controllers.StockProductController;
import com.diana.restaurant.services.implementation.StockProductServiceImpl;
import com.diana.restaurant.services.interfaces.StockProductService;
import com.diana.restaurant.util.fixtures.services.StockProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockProductControllerIntegrationTest {
  private StockProductController stockProductController;
  private StockProductService stockProductServiceSpy;

  @BeforeEach
  public void setUp() {
    StockProductService stockProductService = new StockProductServiceImpl();
    stockProductServiceSpy = Mockito.spy(stockProductService);
    stockProductController = new StockProductController(stockProductServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    stockProductController.add(stockProduct);
    var stockProductFound = stockProductController.findById(stockProduct.getId());
    Assertions.assertEquals(stockProductFound, stockProduct);
    Mockito.verify(stockProductServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(stockProduct.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllStockProducts() {
    var stockProducts = StockProductServiceFixtures.buildStockProducts(3);
    stockProducts.stream().forEach(stockProductController::add);
    var stockProductsFound = stockProductController.getAll();
    Assertions.assertEquals(stockProducts, stockProductsFound);
  }

  @Test
  public void add_ShouldAddStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var stockProductAdded = stockProductController.add(stockProduct);
    Assertions.assertEquals(stockProductAdded, stockProduct);
    Mockito.verify(stockProductServiceSpy, Mockito.times(1)).add(Mockito.eq(stockProduct));
  }

  @Test
  public void delete_ShouldDeleteStockProduct() {
    var stockProducts = StockProductServiceFixtures.buildStockProducts(3);
    stockProducts.stream().forEach(stockProductController::add);
    var deletedStockProduct = stockProductController.deleteById(stockProducts.get(0).getId());
    var deletedStockProductToVerify =
        StockProductServiceFixtures.buildStockProduct(stockProducts.get(0));
    Assertions.assertEquals(deletedStockProduct, stockProducts.get(0));
    Mockito.verify(stockProductServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedStockProductToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateStockProduct() {
    var stockProductToUpdate = StockProductServiceFixtures.buildStockProduct();
    stockProductController.add(stockProductToUpdate);
    stockProductToUpdate.setProduct(null);
    var stockProductToTestVerify =
        StockProductServiceFixtures.buildStockProduct(stockProductToUpdate);
    var updatedStockProduct = stockProductController.update(stockProductToUpdate);
    Assertions.assertEquals(updatedStockProduct, stockProductToUpdate);
    Mockito.verify(stockProductServiceSpy, Mockito.times(1))
        .update(Mockito.eq(stockProductToTestVerify));
  }
}
