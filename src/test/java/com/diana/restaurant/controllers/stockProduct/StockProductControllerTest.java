package com.diana.restaurant.controllers.stockProduct;

import com.diana.restaurant.controllers.StockProductController;
import com.diana.restaurant.services.interfaces.StockProductService;
import com.diana.restaurant.util.fixtures.services.StockProductServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockProductControllerTest {
  private StockProductController stockProductController;
  private StockProductService stockProductServiceMock;

  @BeforeEach
  public void setup() {
    stockProductServiceMock = Mockito.mock(StockProductService.class);
    stockProductController = new StockProductController(stockProductServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfStockProducts() {
    var stockProducts = StockProductServiceFixtures.buildStockProducts(3);
    Mockito.when(stockProductServiceMock.getAll()).thenReturn(stockProducts);
    var stockProductsReturned = stockProductController.getAll();
    Assertions.assertEquals(stockProducts, stockProductsReturned);
    Mockito.verify(stockProductServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var expectedStockProduct = StockProductServiceFixtures.buildStockProduct(1L);
    Mockito.when(stockProductServiceMock.add(Mockito.eq(stockProduct)))
        .thenReturn(expectedStockProduct);
    var stockProductAdded = stockProductController.add(stockProduct);
    Assertions.assertEquals(expectedStockProduct, stockProductAdded);
    Mockito.verify(stockProductServiceMock, Mockito.times(1)).add(Mockito.eq(stockProduct));
  }

  @Test()
  public void delete_ShouldDeleteStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct(1L);
    Mockito.when(stockProductServiceMock.delete(stockProduct.getId())).thenReturn(stockProduct);
    var stockProductDeleted = stockProductController.deleteById(stockProduct.getId());
    Assertions.assertEquals(stockProduct, stockProductDeleted);
    var stockProductToTestVerify = StockProductServiceFixtures.buildStockProduct(stockProduct);
    Mockito.verify(stockProductServiceMock, Mockito.times(1))
        .delete(Mockito.eq(stockProductToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct(1L);
    Mockito.when(stockProductServiceMock.findById(stockProduct.getId()))
        .thenReturn(Optional.of(stockProduct));
    var stockProductFound = stockProductController.findById(stockProduct.getId());
    Assertions.assertEquals(stockProduct, stockProductFound);
    var stockProductToTestVerify = StockProductServiceFixtures.buildStockProduct(stockProduct);
    Mockito.verify(stockProductServiceMock, Mockito.times(1))
        .findById(Mockito.eq(stockProductToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateStockProduct() {
    var stockProductToUpdate = StockProductServiceFixtures.buildStockProduct(1L);
    stockProductToUpdate.setProduct(null);
    Mockito.when(stockProductServiceMock.update(stockProductToUpdate))
        .thenReturn(stockProductToUpdate);
    var stockProductToTestVerify =
        StockProductServiceFixtures.buildStockProduct(stockProductToUpdate);
    var stockProductUpdated = stockProductController.update(stockProductToUpdate);
    Assertions.assertEquals(stockProductToUpdate, stockProductUpdated);
    Mockito.verify(stockProductServiceMock, Mockito.times(1))
        .update(Mockito.eq(stockProductToTestVerify));
  }
}
